package co.id.ionsoft;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import co.id.ionsoft.data.di.ApplicationsComponents;
import co.id.ionsoft.data.di.AppsModule;
import co.id.ionsoft.data.di.DaggerApplicationsComponents;
import timber.log.Timber;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public class Apps extends Application {

    private ApplicationsComponents components;

    @Override
    public void onCreate() {
        super.onCreate();
        initDi();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

    }

    private void initDi() {
        components = DaggerApplicationsComponents.builder()
                .appsModule(new AppsModule(this))
                .build();
    }

    public ApplicationsComponents getComponents() {
        return components;
    }
}
