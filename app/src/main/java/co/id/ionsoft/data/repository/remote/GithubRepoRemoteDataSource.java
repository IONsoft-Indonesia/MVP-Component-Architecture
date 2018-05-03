package co.id.ionsoft.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import co.id.ionsoft.data.api.GithubService;
import co.id.ionsoft.data.model.GithubEntity;
import co.id.ionsoft.data.repository.GithubRepoDataSource;
import co.id.ionsoft.utils.Constant;
import io.reactivex.Flowable;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public class GithubRepoRemoteDataSource implements GithubRepoDataSource {

    private GithubService githubService;

    @Inject
    public GithubRepoRemoteDataSource(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    public Flowable<List<GithubEntity>> loadGithubRepo(boolean forceRemote) {
        return githubService.getAllRepository(Constant.NAME_USER_GITHUB);
    }

    @Override
    public void addGithubRepo(GithubEntity githubEntity) {
        // can add manual ..?
    }

    @Override
    public void clearData() {
        // handle remove adapter data
    }
}
