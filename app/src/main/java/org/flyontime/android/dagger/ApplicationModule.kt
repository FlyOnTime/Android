package org.flyontime.android.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.flyontime.android.model.service.FlyOnTimeAPI
import org.flyontime.android.scheduler.AppSchedulerProvider
import org.flyontime.android.scheduler.SchedulerProvider
import org.flyontime.android.ui.presenter.HomeInteractor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by JW on 27.04.2017.
 */


@Module
class ApplicationModule {

    @Provides
    fun provideSchoolsAPI(retrofit: Retrofit): FlyOnTimeAPI {
        return retrofit.create(FlyOnTimeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        return Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlyOnTimeAPI.URL)
                .build()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideSchoolsInteractor(): HomeInteractor {
        return HomeInteractor(provideSchoolsAPI(provideRetrofit()), provideSchedulerProvider())
    }

}
