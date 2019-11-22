package tmdb.tharindu.movieshowcase.di.module

import tmdb.tharindu.movieshowcase.service.WebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WebService.BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideAppWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }

}