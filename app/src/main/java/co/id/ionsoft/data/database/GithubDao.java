package co.id.ionsoft.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import co.id.ionsoft.data.model.GithubEntity;
import co.id.ionsoft.utils.Constant;
import io.reactivex.Flowable;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */

@Dao
public interface GithubDao {

    @Query("SELECT * FROM " + Constant.TABLE_GITHUB_REPOSITORY)
    Flowable<List<GithubEntity>> getAllRepository();

    @Query("SELECT * FROM " + Constant.TABLE_GITHUB_REPOSITORY + " WHERE id= :id")
    Flowable<List<GithubEntity>> getRepoById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GithubEntity githubEntity);

    @Query("DELETE FROM " + Constant.TABLE_GITHUB_REPOSITORY)
    void deleteAll();
}
