package co.id.ionsoft.ui.main;

import java.util.List;

import co.id.ionsoft.data.model.GithubEntity;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public interface MainView {

    void showRepository(List<GithubEntity> githubEntities);

    void clearRepository();

    void showNoDataMessage();

    void showMessageError(String error);

    void stopLoadingIndicator();

    void showEmptySearchResult();

}
