package de.example.jw.mvi_app.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.example.jw.mvi_app.model.service.SchoolsDataAPI;
import de.example.jw.mvi_app.scheduler.AppSchedulerProvider;
import de.example.jw.mvi_app.scheduler.SchedulerProvider;
import de.example.jw.mvi_app.ui.presenter.HomeInteractor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JW on 27.04.2017.
 */


@Module
public class ApplicationModule {

    @Provides
    public SchoolsDataAPI provideSchoolsAPI(Retrofit retrofit) {
        return retrofit.create(SchoolsDataAPI.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SchoolsDataAPI.URL)
                .build();
    }

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    public HomeInteractor provideSchoolsInteractor() {
        return new HomeInteractor(provideSchoolsAPI(provideRetrofit()), provideSchedulerProvider());
    }

}
