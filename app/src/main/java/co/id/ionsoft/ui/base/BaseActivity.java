package co.id.ionsoft.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import co.id.ionsoft.Apps;
import co.id.ionsoft.data.di.ApplicationsComponents;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public class BaseActivity extends AppCompatActivity {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected ApplicationsComponents getApplicationComponent() {
        return ((Apps) getApplication()).getComponents();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
