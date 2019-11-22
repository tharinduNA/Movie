package tmdb.tharindu.movieshowcase.di.module

import tmdb.tharindu.movieshowcase.fragment.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesFragmentProvider {

    @ContributesAndroidInjector(modules =[(MoviesFragmentModule::class)])
    internal abstract fun provideMainFragmentFactory(): MoviesFragment

}