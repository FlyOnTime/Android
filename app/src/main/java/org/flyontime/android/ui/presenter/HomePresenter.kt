package org.flyontime.android.ui.presenter


import org.flyontime.android.FlyOnTimeApplication
import org.flyontime.android.model.service.FlyOnTimeAPI
import org.flyontime.android.scheduler.SchedulerProvider
import org.flyontime.android.ui.contract.HomeViewPresenterContract

import javax.inject.Inject

/**
 * Created by JW on 24.04.2017.
 */

class HomePresenter : HomeViewPresenterContract.HomePresenterActions {

    private val TAG = this.javaClass.simpleName

    @Inject
    internal lateinit var api: FlyOnTimeAPI

    @Inject
    internal lateinit var schedulerProvider: SchedulerProvider

    @Inject
    internal lateinit var interactor: HomeInteractor

    private var view: HomeViewPresenterContract.HomeViewActions

    constructor(view: HomeViewPresenterContract.HomeViewActions) {
        this.view = view
        FlyOnTimeApplication.getApplication().component.inject(this)
    }

    constructor(view: HomeViewPresenterContract.HomeViewActions, schedulerProvider: SchedulerProvider, api: FlyOnTimeAPI) {
        this.view = view
        this.interactor = HomeInteractor(api, schedulerProvider)
        this.api = api
        FlyOnTimeApplication.getApplication().component.inject(this)
    }

    override fun goodRxloadSchools() {
        // Subscribe to the Observable from the interactor and on state change render the state in the view
        // interactor#loadSchools is an observable that emits states based on the data returned by the API
        interactor.loadSchools().subscribe { state -> view.render(state) }
    }
}
