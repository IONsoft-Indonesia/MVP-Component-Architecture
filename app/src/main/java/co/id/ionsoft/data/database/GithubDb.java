package co.id.ionsoft.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import co.id.ionsoft.data.model.GithubEntity;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Database(entities = GithubEntity.class, version = 1, exportSchema = false)
public abstract class GithubDb extends RoomDatabase {

    public abstract GithubDao githubDao();

}
