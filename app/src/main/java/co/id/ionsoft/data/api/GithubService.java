package co.id.ionsoft.data.api;

import java.util.List;

import co.id.ionsoft.data.model.GithubEntity;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public interface GithubService {

    @GET("/users/{user}/repos")
    Flowable<List<GithubEntity>> getAllRepository(@Path("user") String user);

}
