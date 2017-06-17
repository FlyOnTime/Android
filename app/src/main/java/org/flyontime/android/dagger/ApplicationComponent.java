package org.flyontime.android.dagger;

import org.flyontime.android.FlyOnTimeApplication;
import org.flyontime.android.ui.presenter.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JW on 27.04.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(HomePresenter presenter);

    void inject(FlyOnTimeApplication application);

}
