package org.flyontime.android

import android.app.Application

import com.estimote.coresdk.common.config.EstimoteSDK

import org.flyontime.android.dagger.ApplicationComponent
import org.flyontime.android.dagger.DaggerApplicationComponent
import org.flyontime.jw.android.R

import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Created by JW on 29.04.2017.
 */

class FlyOnTimeApplication : Application() {

    var component: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        application = this

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        //  To get your AppId and AppToken you need to create new application in Estimote Cloud.
        EstimoteSDK.initialize(applicationContext, getString(R.string.estimote_appid), getString(R.string.estimote_token))
        // Optional, debug logging.
        EstimoteSDK.enableDebugLogging(true)

        initComponent()
    }

    private fun initComponent() {
        component = DaggerApplicationComponent.create()
    }

    companion object {

        var application: FlyOnTimeApplication? = null
            private set
    }
}
