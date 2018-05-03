package co.id.ionsoft.ui.main;

import co.id.ionsoft.data.di.ApplicationsComponents;
import co.id.ionsoft.ui.base.ActivityScope;
import co.id.ionsoft.utils.shcedulers.SchedulerModule;
import dagger.Component;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */

@ActivityScope
@Component(modules = {MainPresenterModule.class,
        SchedulerModule.class},
        dependencies = {ApplicationsComponents.class})
public interface GithubRepoComponent {
    void inject(MainActivity mainActivity);
}
