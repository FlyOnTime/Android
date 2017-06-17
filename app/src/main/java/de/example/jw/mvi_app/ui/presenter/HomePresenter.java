package de.example.jw.mvi_app.ui.presenter;


import android.util.Log;

import javax.inject.Inject;

import de.example.jw.mvi_app.FlyOnTimeApplication;
import de.example.jw.mvi_app.model.data.SchoolsModel;
import de.example.jw.mvi_app.model.service.SchoolsDataAPI;
import de.example.jw.mvi_app.model.state.SchoolsViewState;
import de.example.jw.mvi_app.scheduler.SchedulerProvider;
import de.example.jw.mvi_app.ui.contract.HomeViewPresenterContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    public void normalRxLoadSchools() {

        //Subscribe to the Observable from the API
        api.getHamburgSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(onSubscribeEvent -> {
                    // Create a loading state
                    // Loading = true, Error = null, Data = null
                    SchoolsViewState state = SchoolsViewState.LoadingState();
                    view.render(state);
                })
                .subscribe(
                        schools -> {
                            //Create a not loading state with school data
                            // Loading = false, error = null, data = schools
                            SchoolsViewState state = SchoolsViewState.DataLoadedState(schools);
                            view.render(state);
                            for (SchoolsModel school : schools) {
                                Log.d(TAG, school.getName());
                            }
                        },
                        error -> {
                            Log.d(TAG, error.getMessage());
                            //Create an error state
                            // Loading = false, error = error, data = null
                            SchoolsViewState state = SchoolsViewState.ErrorState(error);
                            view.render(state);
                        }
                );
    }

    @Override
    public void goodRxloadSchools() {
        // Subscribe to the Observable from the interactor and on state change render the state in the view
        // interactor#loadSchools is an observable that emits states based on the data returned by the API
        interactor.loadSchools().subscribe(state -> view.render(state));
    }
}
