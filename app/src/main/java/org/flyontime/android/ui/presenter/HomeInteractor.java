package org.flyontime.android.ui.presenter;

import org.flyontime.android.model.data.DashboardModelInterface;
import org.flyontime.android.model.data.DateModel;
import org.flyontime.android.model.data.ItemModel;
import org.flyontime.android.model.service.SchoolsDataAPI;
import org.flyontime.android.model.state.HomeViewState;
import org.flyontime.android.scheduler.SchedulerProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by JW on 29.04.2017.
 */

public class HomeInteractor {

    ArrayList<DashboardModelInterface> items = new ArrayList<>();
    private SchoolsDataAPI api;
    private SchedulerProvider schedulerProvider;
    private Observable<List<DashboardModelInterface>> stuff = Observable.fromArray(items);

    @Inject
    public HomeInteractor(SchoolsDataAPI api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
        items.add(new DateModel(new Date()));
        items.add(new ItemModel("Check-in online", "check more luggage, buy seats and select preferred services", "11:30", false));
        items.add(new ItemModel("Prepare for the trip", "checked luggage: 2 bags (max. 40 kg/bag)special item: 1 bicycle (max. 20 kg)", "12:42", true));
        items.add(new ItemModel("Travel to the airport", "estimated daparture at 11:30travel by car (36 mins)", "13:10", true));

    }

    Observable<HomeViewState> loadSchools() {
        //return api.getHamburgSchools() //gives us an Observable<List<SchoolsModel>>
        return stuff
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                // Use the data we get from the network to create a new state model with the data and emit it to the data flow
                .map(HomeViewState::DataLoadedState)
                // The first state is always a loading state with no error and no data
                //.startWith(new HomeViewState(true, null, null))
                .startWith(HomeViewState.LoadingState())
                // When there is an error, we emit a state model with an error in it
                .onErrorReturn(HomeViewState::ErrorState);
    }

}
