package tmdb.tharindu.movieshowcase.di.module

import tmdb.tharindu.movieshowcase.service.AppRepository
import tmdb.tharindu.movieshowcase.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(appRepository: AppRepository): MainViewModel {
        return MainViewModel(appRepository)
    }

}