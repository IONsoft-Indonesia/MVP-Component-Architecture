package co.id.ionsoft.data.repository.local;

import java.util.List;

import javax.inject.Inject;

import co.id.ionsoft.data.database.GithubDao;
import co.id.ionsoft.data.model.GithubEntity;
import co.id.ionsoft.data.repository.GithubRepoDataSource;
import io.reactivex.Flowable;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public class GithubRepoLocalDataSource implements GithubRepoDataSource {

    private GithubDao mGithubDao;

    @Inject
    public GithubRepoLocalDataSource(GithubDao mGithubDao) {
        this.mGithubDao = mGithubDao;
    }

    @Override
    public Flowable<List<GithubEntity>> loadGithubRepo(boolean forceRemote) {
        return mGithubDao.getAllRepository();
    }

    @Override
    public void addGithubRepo(GithubEntity githubEntity) {
        mGithubDao.insert(githubEntity);
    }

    @Override
    public void clearData() {
        mGithubDao.deleteAll();
    }
}
