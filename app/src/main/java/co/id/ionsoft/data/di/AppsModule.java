package co.id.ionsoft.data.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Module
public class AppsModule {

    private Context mContext;

    public AppsModule(Application mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }
}
