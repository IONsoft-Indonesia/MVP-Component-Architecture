package co.id.ionsoft.ui.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import javax.inject.Inject;

import co.id.ionsoft.data.model.GithubEntity;
import co.id.ionsoft.data.repository.GithubDataSource;
import co.id.ionsoft.utils.shcedulers.RunOn;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static co.id.ionsoft.utils.shcedulers.SchedulerType.IO;
import static co.id.ionsoft.utils.shcedulers.SchedulerType.UI;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */

public class MainPresenterImpl implements MainPresenter, LifecycleObserver {

    private GithubDataSource githubDataSource;
    private MainView view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposable;

    @Inject
    MainPresenterImpl(GithubDataSource githubDataSource, MainView view,
                      @RunOn(IO) Scheduler ioScheduler, @RunOn(UI) Scheduler uiScheduler) {
        this.githubDataSource = githubDataSource;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
        disposable = new CompositeDisposable();
    }


    @Override
    public void loadGithubRepo(boolean forceRemote) {
        view.clearRepository();

        Disposable disposables = githubDataSource.loadGithubRepo(forceRemote)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handlerError, () -> view.stopLoadingIndicator());
        disposable.add(disposables);

    }

    private void handlerError(Throwable throwable) {
        view.stopLoadingIndicator();
        view.showMessageError(throwable.getLocalizedMessage());
    }


    private void handleReturnedData(List<GithubEntity> list) {
        view.stopLoadingIndicator();
        if (list != null && !list.isEmpty()) {
            view.showRepository(list);
        } else {
            view.showNoDataMessage();
        }
    }


    @Override
    public void search(String query) {
        Disposable disposables = githubDataSource.loadGithubRepo(false)
                .flatMap(Flowable::fromIterable)
                .filter(githubEntity -> githubEntity.getName() != null)
                .filter(githubEntity -> githubEntity.getName().toLowerCase().contains(query.toLowerCase()))
                .toList()
                .toFlowable()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(githubEntity -> {
                    if (githubEntity.isEmpty()) {
                        // Clear old data in view
                        view.clearRepository();
                        // Show notification
                        view.showEmptySearchResult();
                    } else {
                        // Update filtered data
                        view.showRepository(githubEntity);
                    }
                });

        disposable.add(disposables);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        loadGithubRepo(false);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposable.clear();
    }
}
