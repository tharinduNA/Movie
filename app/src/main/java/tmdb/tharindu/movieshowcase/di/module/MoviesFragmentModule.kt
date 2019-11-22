package tmdb.tharindu.movieshowcase.di.module

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.GridLayoutManager
import dagger.Module
import dagger.Provides
import tmdb.tharindu.movieshowcase.ViewModelProviderFactory
import tmdb.tharindu.movieshowcase.adapter.MovieAdapter
import tmdb.tharindu.movieshowcase.adapter.MovieAdapter.MovieAdapterListener
import tmdb.tharindu.movieshowcase.fragment.MoviesFragment
import tmdb.tharindu.movieshowcase.service.AppRepository
import tmdb.tharindu.movieshowcase.utility.GridSpacingItemDecoration
import tmdb.tharindu.movieshowcase.utility.OnBottomReachedListener
import tmdb.tharindu.movieshowcase.viewmodel.MoviesFragmentViewModel
import javax.inject.Singleton

@Module
class MoviesFragmentModule {

    @Provides
    internal fun provideMainFragmentViewModel(appRepository: AppRepository): MoviesFragmentViewModel {
        return MoviesFragmentViewModel(appRepository)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager(fragment.activity!!,2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2,5,true)
    }

    @Provides
    internal fun provideMovieAdapter(): MovieAdapter {
        return MovieAdapter(ArrayList())
    }

    @Provides
    @Singleton
    internal fun provideMovieAdapterListener(listener: MovieAdapterListener): MovieAdapterListener {
        return listener
    }


    @Provides
    internal fun mainFragmentViewModelProvider(moviesFragmentViewModel: MoviesFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesFragmentViewModel)
    }

    @Provides
    @Singleton
    internal fun bottomReachedListener(onBottomReachedListener: OnBottomReachedListener): OnBottomReachedListener {
        return onBottomReachedListener
    }

}