package de.example.jw.mvi_app.ui.presenter;

import javax.inject.Inject;

import de.example.jw.mvi_app.model.service.SchoolsDataAPI;
import de.example.jw.mvi_app.model.state.SchoolsViewState;
import de.example.jw.mvi_app.scheduler.SchedulerProvider;
import io.reactivex.Observable;

/**
 * Created by JW on 29.04.2017.
 */

public class HomeInteractor {

    private SchoolsDataAPI api;

    private SchedulerProvider schedulerProvider;

    @Inject
    public HomeInteractor(SchoolsDataAPI api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;
    }

    Observable<SchoolsViewState> loadSchools() {
        return api.getHamburgSchools() //gives us an Observable<List<SchoolsModel>>
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                // Use the data we get from the network to create a new state model with the data and emit it to the data flow
                .map(SchoolsViewState::DataLoadedState)
                // The first state is always a loading state with no error and no data
                //.startWith(new SchoolsViewState(true, null, null))
                .startWith(SchoolsViewState.LoadingState())
                // When there is an error, we emit a state model with an error in it
                .onErrorReturn(SchoolsViewState::ErrorState);
    }

}
