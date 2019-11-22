package tmdb.tharindu.movieshowcase.di.module

import tmdb.tharindu.movieshowcase.fragment.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailFragmentProvider {

    @ContributesAndroidInjector(modules =[(DetailFragmentModule::class)])
    internal abstract fun provideDetailFragmentFactory(): DetailFragment

}