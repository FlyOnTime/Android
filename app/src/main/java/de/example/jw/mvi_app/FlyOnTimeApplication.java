package de.example.jw.mvi_app;

import android.app.Application;

import de.example.jw.mvi_app.dagger.ApplicationComponent;
import de.example.jw.mvi_app.dagger.DaggerApplicationComponent;


/**
 * Created by JW on 29.04.2017.
 */

public class FlyOnTimeApplication extends Application {

    private static FlyOnTimeApplication application;

    private ApplicationComponent component;

    public static FlyOnTimeApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        initComponent();
    }

    private void initComponent() {
        component = DaggerApplicationComponent.create();
    }


    public ApplicationComponent getComponent() {
        return component;
    }
}
