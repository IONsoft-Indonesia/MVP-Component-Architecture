package co.id.ionsoft.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.ionsoft.R;
import co.id.ionsoft.data.model.GithubEntity;
import co.id.ionsoft.ui.adapters.MainRecyclerViewAdapter;
import co.id.ionsoft.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.recyclerRepo)
    RecyclerView recyclerRepo;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.txtNotif)
    TextView txtNotif;

    @Inject
    MainPresenterImpl presenter;

    private MainRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle(getString(R.string.search));
        initPresenter();
        setupWidgets();
    }

    private void initPresenter() {
        DaggerGithubRepoComponent.builder()
                .mainPresenterModule(new MainPresenterModule(this))
                .applicationsComponents(getApplicationComponent())
                .build()
                .inject(this);
    }



    private void setupWidgets() {
        // Setup recycler view
        adapter = new MainRecyclerViewAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerRepo.setLayoutManager(layoutManager);
        recyclerRepo.setAdapter(adapter);
        recyclerRepo.setItemAnimator(new DefaultItemAnimator());
        adapter.setListener(this::intentOpenUrl);

        // Refresh layout
        swipeToRefresh.setOnRefreshListener(() -> presenter.loadGithubRepo(true));
        // Set notification text visible first
        txtNotif.setVisibility(View.GONE);
    }

    private void intentOpenUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repo, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.search));
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                presenter.search(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void showRepository(List<GithubEntity> githubEntities) {
        txtNotif.setVisibility(View.GONE);
        adapter.replaceData(githubEntities);
    }

    @Override
    public void clearRepository() {
        adapter.clearData();
    }

    @Override
    public void showNoDataMessage() {
        showNotification(getString(R.string.msg_no_data));
    }

    @Override
    public void showMessageError(String error) {
        showNotification(error);
    }

    @Override
    public void stopLoadingIndicator() {
        if (swipeToRefresh.isRefreshing()) {
            swipeToRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showEmptySearchResult() {
        showNotification(getString(R.string.msg_empty_search_result));
    }

    private void showNotification(String message) {
        txtNotif.setVisibility(View.VISIBLE);
        txtNotif.setText(message);
    }
}
