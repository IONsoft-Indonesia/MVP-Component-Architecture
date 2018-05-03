package co.id.ionsoft.data.repository;

import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.id.ionsoft.data.model.GithubEntity;
import co.id.ionsoft.data.repository.local.Local;
import co.id.ionsoft.data.repository.remote.Remote;
import io.reactivex.Flowable;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public class GithubDataSource implements GithubRepoDataSource {

    private GithubRepoDataSource localDataSource;
    private GithubRepoDataSource remoteDataSource;

    @VisibleForTesting
    private List<GithubEntity> caches;

    @Inject
    public GithubDataSource(@Local GithubRepoDataSource localDataSource,
                            @Remote GithubRepoDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;

        caches = new ArrayList<>();
    }

    @Override
    public Flowable<List<GithubEntity>> loadGithubRepo(boolean forceRemote) {
        if (forceRemote) {
            return refreshData();
        } else {
            if (caches.size() > 0) {
                return Flowable.just(caches);
            } else {
                return localDataSource.loadGithubRepo(false)
                        .take(1)
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(githubEntities -> caches.add(githubEntities))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        .switchIfEmpty(refreshData());
            }

        }
    }

    private Flowable<List<GithubEntity>> refreshData() {
        return remoteDataSource
                .loadGithubRepo(true)
                .doOnNext(list -> {
                    caches.clear();
                    localDataSource.clearData();
                })
                .flatMap(Flowable::fromIterable)
                .doOnNext(githubEntity -> {
                    caches.add(githubEntity);
                    localDataSource.addGithubRepo(githubEntity);
                }).toList().toFlowable();
    }

    public Flowable<GithubEntity> getGithubRepoById(long repoId) {
        return Flowable.fromIterable(caches).filter(githubEntity -> githubEntity.getId() == repoId);
    }

    @Override
    public void addGithubRepo(GithubEntity githubEntity) {
        // next step
    }

    @Override
    public void clearData() {
        caches.clear();
        localDataSource.clearData();
    }
}
