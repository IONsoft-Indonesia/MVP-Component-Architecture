package co.id.ionsoft.utils.shcedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static co.id.ionsoft.utils.shcedulers.SchedulerType.IO;
import static co.id.ionsoft.utils.shcedulers.SchedulerType.UI;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Module
public class SchedulerModule {

    @Provides
    @RunOn(IO)
    Scheduler provideIo() {
        return Schedulers.io();
    }

    @Provides
    @RunOn(UI)
    Scheduler provideUi() {
        return AndroidSchedulers.mainThread();
    }
}
