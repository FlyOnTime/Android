package org.flyontime.android.dagger;

import org.flyontime.android.model.service.FlyOnTimeAPI;
import org.flyontime.android.scheduler.AppSchedulerProvider;
import org.flyontime.android.scheduler.SchedulerProvider;
import org.flyontime.android.ui.presenter.HomeInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JW on 27.04.2017.
 */


@Module
public class ApplicationModule {

    @Provides
    public FlyOnTimeAPI provideSchoolsAPI(Retrofit retrofit) {
        return retrofit.create(FlyOnTimeAPI.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlyOnTimeAPI.URL)
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
