package co.id.ionsoft.data.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import co.id.ionsoft.data.database.GithubDao;
import co.id.ionsoft.data.database.GithubDb;
import co.id.ionsoft.utils.Constant;
import dagger.Module;
import dagger.Provides;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Module
public class DatabaseModule {

    private static final String DATABASE= "database_name";

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return Constant.DATABASE_NAME;
    }


    @Provides
    @Singleton
    GithubDb provideGithubDb(Context mContext, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(mContext, GithubDb.class, databaseName).build();
    }

    @Provides
    @Singleton
    GithubDao provideGithubDao(GithubDb mGithubDb) {
        return mGithubDb.githubDao();
    }
}
