package co.id.ionsoft.ui.main;

import co.id.ionsoft.ui.base.BasePresenter;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public interface MainPresenter extends BasePresenter<MainView> {

    void loadGithubRepo(boolean forceRemote);

    void search(String query);
}
