package co.id.ionsoft.data.di;

import javax.inject.Singleton;

import co.id.ionsoft.data.repository.GithubRepoDataSource;
import co.id.ionsoft.data.repository.local.GithubRepoLocalDataSource;
import co.id.ionsoft.data.repository.local.Local;
import co.id.ionsoft.data.repository.remote.GithubRepoRemoteDataSource;
import co.id.ionsoft.data.repository.remote.Remote;
import dagger.Module;
import dagger.Provides;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Module
public class GithubRepoDataModule {

    @Provides
    @Local
    @Singleton
    public GithubRepoDataSource provideLocalDataSource(GithubRepoLocalDataSource localDataSource) {
        return localDataSource;
    }


    @Provides
    @Remote
    @Singleton
    public GithubRepoDataSource provideRemoteDataSource(GithubRepoRemoteDataSource repoRemoteDataSource) {
        return repoRemoteDataSource;
    }
}
