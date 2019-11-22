package tmdb.tharindu.movieshowcase.di.builder

import tmdb.tharindu.movieshowcase.activity.MainActivity
import tmdb.tharindu.movieshowcase.di.module.MainActivityModule
import tmdb.tharindu.movieshowcase.di.module.DetailFragmentProvider
import tmdb.tharindu.movieshowcase.di.module.MoviesFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class),
        (MoviesFragmentProvider::class),
        (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}