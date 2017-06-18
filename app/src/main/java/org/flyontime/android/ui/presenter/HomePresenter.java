package org.flyontime.android.ui.presenter;


import org.flyontime.android.FlyOnTimeApplication;
import org.flyontime.android.model.data.FlyOnTime.ItemModel;
import org.flyontime.android.model.service.FlyOnTimeAPI;
import org.flyontime.android.scheduler.SchedulerProvider;
import org.flyontime.android.ui.contract.HomeViewPresenterContract;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by JW on 24.04.2017.
 */

public class HomePresenter implements HomeViewPresenterContract.SchoolsPresenterActions {

    private final String TAG = this.getClass().getSimpleName();

    @Inject
    FlyOnTimeAPI api;

    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    HomeInteractor interactor;

    private HomeViewPresenterContract.SchoolsViewActions view;

    public HomePresenter(HomeViewPresenterContract.SchoolsViewActions view) {
        this.view = view;
        FlyOnTimeApplication.getApplication().getComponent().inject(this);
    }

    public HomePresenter(HomeViewPresenterContract.SchoolsViewActions view, SchedulerProvider schedulerProvider, FlyOnTimeAPI api) {
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

    public void doCoolStuff() {
        ArrayList<ItemModel> mItems = new ArrayList<>();
        ArrayList<String> mItemNames = new ArrayList<>();
        mItemNames.add("Check-in online");
        mItemNames.add("Prepare for the trip");
        mItemNames.add("Travel to the airport");
        mItemNames.add("Baggage drop-off");
        mItemNames.add("Pass security check");
        mItemNames.add("Shop and relax");
        mItemNames.add("Board on the plane");
        api.getTravelInfo().subscribe(
                data -> {
                    System.out.println(data);
                    for (int i = 0; i <= mItemNames.size(); i++) {

                        //mItems.add(new ItemModel(data.get(i)));
                    }
                }
        );
    }
}
