package org.flyontime.android.ui.presenter;

import org.flyontime.android.model.data.FlyOnTime.DashboardModelInterface;
import org.flyontime.android.model.data.FlyOnTime.ItemModel;
import org.flyontime.android.model.data.FlyOnTime.MainRequestBody;
import org.flyontime.android.model.service.FlyOnTimeAPI;
import org.flyontime.android.model.state.HomeViewState;
import org.flyontime.android.scheduler.SchedulerProvider;
import org.flyontime.android.ui.adapter.CardType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by JW on 29.04.2017.
 */

public class HomeInteractor {

    ArrayList<DashboardModelInterface> items = new ArrayList<>();
    private FlyOnTimeAPI api;
    private SchedulerProvider schedulerProvider;
    private Observable<List<DashboardModelInterface>> stuff;

    @Inject
    public HomeInteractor(FlyOnTimeAPI api, SchedulerProvider schedulerProvider) {
        this.api = api;
        this.schedulerProvider = schedulerProvider;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        /*try {
            items.add(new DateModel(simpleDateFormat.parse("24.07.2017")));
            items.add(new ItemModel("Check-in online", "check more luggage, buy seats and select preferred services", "", false, CardType.CHECKIN, 2, 40));
            items.add(new ItemModel("Prepare for the trip", "checked luggage: 2 bags (max. 40 kg/bag)special item: 1 bicycle (max. 20 kg)", "", true, CardType.PREPARE));
            items.add(new DateModel(simpleDateFormat.parse("25.07.2017")));
            items.add(new ItemModel("Travel to the airport", "estimated daparture at 11:30travel by car (36 mins)", "13:10", true, CardType.COMMUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        stuff = Observable.fromArray(items);

    }

    Observable<HomeViewState> loadSchools() {
        //return api.getTravelInfo() //gives us an Observable<List<SchoolsModel>>
        /*return stuff
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                // Use the data we get from the network to create a new state model with the data and emit it to the data flow
                .map(HomeViewState::DataLoadedState)
                // The first state is always a loading state with no error and no data
                //.startWith(new HomeViewState(true, null, null))
                .startWith(HomeViewState.LoadingState())
                // When there is an error, we emit a state model with an error in it
                .onErrorReturn(HomeViewState::ErrorState);*/
        return loadTravelInfo();
    }

    Observable<HomeViewState> loadTravelInfo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        SimpleDateFormat parserFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSX");
        return api.getTravelInfoPost(new MainRequestBody("KL", "3099", "2017-06-18", 1, 0))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                // Use the data we get from the network to create a new state model with the data and emit it to the data flow
                .map(data -> {
                    ArrayList<DashboardModelInterface> myitems = new ArrayList<>();
                    int l = CardType.values().length;
                    String[] times = {"11:30", "12:00", "12:30", "13:05", "13:15", "13:20", "13:50"};
                    for (int i = 0; i < l; i++) {
                        String cardTitle = CardType.values()[i].toString();
                        CardType cardType = CardType.values()[i];
                        Integer watingTime = Integer.parseInt(data.getEstimatedSecurityCheckWaitingTimeSeconds()) / 60;
                        Integer acc = Integer.parseInt(data.getEstimatedSecurityCheckWaitingTimeAccuracy().replace("%", ""));

                        myitems.add(new ItemModel(cardTitle, times[i], true, cardType, data.getLuggageInformation().getItemNumber(), 20, (Integer.parseInt(data.getEstimatedWaitingTimeTotalSeconds()) - Integer.parseInt(data.getEstimatedSecurityCheckWaitingTimeSeconds())) / 60, acc, simpleDateFormat.format(parserFormat.parse(data.getCheckinStartTime())), simpleDateFormat.format(parserFormat.parse(data.getCheckinEndTime())), watingTime, acc));
                    }
                    return HomeViewState.DataLoadedState(myitems);
                })
                // The first state is always a loading state with no error and no data
                .startWith(HomeViewState.LoadingState())
                // When there is an error, we emit a state model with an error in it
                .onErrorReturn(HomeViewState::ErrorState);
    }

}
