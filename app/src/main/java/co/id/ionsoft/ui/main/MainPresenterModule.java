package co.id.ionsoft.ui.main;

import dagger.Module;
import dagger.Provides;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
@Module
public class MainPresenterModule {

    private MainView viewContract;

    MainPresenterModule(MainView viewContract) {
        this.viewContract = viewContract;
    }

    @Provides
    public MainView provideView(){
        return viewContract;
    }

}
