package tmdb.tharindu.movieshowcase.di.module

import tmdb.tharindu.movieshowcase.service.AppRepository
import tmdb.tharindu.movieshowcase.service.WebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    internal fun provideAppRepository(webService: WebService): AppRepository {
        return AppRepository(webService)
    }

}