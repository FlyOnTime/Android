package org.flyontime.android.ui.presenter;


import org.flyontime.android.FlyOnTimeApplication;
import org.flyontime.android.model.service.SchoolsDataAPI;
import org.flyontime.android.scheduler.SchedulerProvider;
import org.flyontime.android.ui.contract.HomeViewPresenterContract;

import javax.inject.Inject;

/**
 * Created by JW on 24.04.2017.
 */

public class HomePresenter implements HomeViewPresenterContract.SchoolsPresenterActions {

    private final String TAG = this.getClass().getSimpleName();

    @Inject
    SchoolsDataAPI api;

    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    HomeInteractor interactor;

    private HomeViewPresenterContract.SchoolsViewActions view;

    public HomePresenter(HomeViewPresenterContract.SchoolsViewActions view) {
        this.view = view;
        FlyOnTimeApplication.getApplication().getComponent().inject(this);
    }

    public HomePresenter(HomeViewPresenterContract.SchoolsViewActions view, SchedulerProvider schedulerProvider, SchoolsDataAPI api) {
        this.view = view;
        this.interactor = new HomeInteractor(api, schedulerProvider);
        this.api = api;
    }

    @Override
    public void goodRxloadSchools() {
        // Subscribe to the Observable from the interactor and on state change render the state in the view
        // interactor#loadSchools is an observable that emits states based on the data returned by the API
        interactor.loadSchools().subscribe(state -> view.render(state));
    }
}
