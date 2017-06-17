package de.example.jw.mvi_app.dagger;

import javax.inject.Singleton;

import dagger.Component;
import de.example.jw.mvi_app.FlyOnTimeApplication;
import de.example.jw.mvi_app.ui.presenter.HomePresenter;

/**
 * Created by JW on 27.04.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(HomePresenter presenter);

    void inject(FlyOnTimeApplication application);

}
