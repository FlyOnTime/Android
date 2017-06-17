package org.flyontime.jw.android;

import org.flyontime.jw.android.dagger.ApplicationComponent;
import org.flyontime.jw.android.dagger.ApplicationModule;
import org.flyontime.jw.android.model.service.SchoolsDataAPI;
import org.flyontime.jw.android.model.state.SchoolsViewState;
import org.flyontime.jw.android.rx.TestSchedulerProvider;
import org.flyontime.jw.android.ui.contract.HomeViewPresenterContract;
import org.flyontime.jw.android.ui.presenter.HomePresenter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;
import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.flyontime.jw.android.MockUtils.generateFakeList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by JW on 27.04.2017.
 */

public class HomeActivityTest {

    @Rule
    public DaggerMockRule<ApplicationComponent> rule = new DaggerMockRule<>(ApplicationComponent.class, new ApplicationModule())
            .set(component -> {
            });

    @Mock
    private SchoolsDataAPI api;

    @Mock
    private HomeViewPresenterContract.SchoolsViewActions schoolsView;

    private HomeViewPresenterContract.SchoolsPresenterActions schoolsPresenter;

    @Before
    public void setUp() {
        //api = Mockito.mock(SchoolsDataAPI.class);
        schoolsPresenter = new HomePresenter(schoolsView, new TestSchedulerProvider(), api);
    }


    @Test
    public void loadSchoolsSuccessful() {

        when(api.getHamburgSchools()).thenReturn(Observable.just(generateFakeList()));
        schoolsPresenter.goodRxloadSchools();

        verify(schoolsView).render(SchoolsViewState.LoadingState());
        verify(schoolsView).render(SchoolsViewState.DataLoadedState(generateFakeList()));

    }

    @Test
    public void loadSchoolsNotSuccessful() {
        Throwable throwable = new Throwable();

        when(api.getHamburgSchools()).thenReturn(Observable.error(throwable));
        schoolsPresenter.goodRxloadSchools();

        verify(schoolsView).render(SchoolsViewState.LoadingState());
        verify(schoolsView).render(SchoolsViewState.ErrorState(throwable));
    }

}
