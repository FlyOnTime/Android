package org.flyontime.android;

import android.app.Application;

import com.estimote.coresdk.common.config.EstimoteSDK;

import org.flyontime.android.dagger.ApplicationComponent;
import org.flyontime.android.dagger.DaggerApplicationComponent;
import org.flyontime.jw.android.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


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

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        //  To get your AppId and AppToken you need to create new application in Estimote Cloud.
        EstimoteSDK.initialize(getApplicationContext(), "flyontime-cz7", "8f63c2308eec235ddf702c537a6f7677");
        // Optional, debug logging.
        EstimoteSDK.enableDebugLogging(true);

        initComponent();
    }

    private void initComponent() {
        component = DaggerApplicationComponent.create();
    }


    public ApplicationComponent getComponent() {
        return component;
    }
}
