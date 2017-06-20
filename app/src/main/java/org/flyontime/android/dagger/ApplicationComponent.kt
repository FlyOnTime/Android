package org.flyontime.android.dagger

import dagger.Component
import org.flyontime.android.FlyOnTimeApplication
import org.flyontime.android.ui.presenter.FetchEmailsPresenter
import org.flyontime.android.ui.presenter.HomePresenter
import javax.inject.Singleton

/**
 * Created by JW on 27.04.2017.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(presenter: HomePresenter)

    fun inject(presenter: FetchEmailsPresenter)

    fun inject(application: FlyOnTimeApplication)

}
