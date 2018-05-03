package co.id.ionsoft.data.repository;

import java.util.List;

import co.id.ionsoft.data.model.GithubEntity;
import io.reactivex.Flowable;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public interface GithubRepoDataSource {

    Flowable<List<GithubEntity>> loadGithubRepo(boolean forceRemote);

    void addGithubRepo(GithubEntity githubEntity);

    void clearData();
}
