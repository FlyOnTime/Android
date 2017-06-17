package de.example.jw.mvi_app;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import de.example.jw.mvi_app.dagger.ApplicationComponent;
import de.example.jw.mvi_app.dagger.ApplicationModule;
import de.example.jw.mvi_app.model.service.SchoolsDataAPI;
import de.example.jw.mvi_app.model.state.SchoolsViewState;
import de.example.jw.mvi_app.rx.TestSchedulerProvider;
import de.example.jw.mvi_app.ui.contract.HomeViewPresenterContract;
import de.example.jw.mvi_app.ui.presenter.HomePresenter;
import io.reactivex.Observable;
import it.cosenonjaviste.daggermock.DaggerMockRule;

import static de.example.jw.mvi_app.MockUtils.generateFakeList;
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
