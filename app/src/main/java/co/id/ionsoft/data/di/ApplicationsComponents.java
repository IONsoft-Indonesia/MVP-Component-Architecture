package co.id.ionsoft.data.di;

import javax.inject.Singleton;

import co.id.ionsoft.data.repository.GithubDataSource;
import dagger.Component;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */

@Singleton
@Component(modules = {GithubRepoDataModule.class,
        AppsModule.class,
        ApiServiceModule.class,
        DatabaseModule.class
        })
public interface ApplicationsComponents {

    GithubDataSource provideGithubDataSource();
}
