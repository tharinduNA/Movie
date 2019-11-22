package tmdb.tharindu.movieshowcase.di.module

import tmdb.tharindu.movieshowcase.ViewModelProviderFactory
import tmdb.tharindu.movieshowcase.service.AppRepository
import tmdb.tharindu.movieshowcase.fragment.DetailFragment
import tmdb.tharindu.movieshowcase.viewmodel.DetailFragmentViewModel
import tmdb.tharindu.movieshowcase.adapter.SimilarMovieAdapter
import tmdb.tharindu.movieshowcase.utility.GridSpacingItemDecoration
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.GridLayoutManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailFragmentModule {


    @Provides
    internal fun provideDetailFragmentViewModel(appRepository: AppRepository): DetailFragmentViewModel {
        return DetailFragmentViewModel(appRepository)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: DetailFragment): GridLayoutManager {
        return GridLayoutManager(fragment.activity!!,1)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2,1,true)
    }


    @Provides
    internal fun provideSimilarMovieAdapter(): SimilarMovieAdapter {
        return SimilarMovieAdapter(ArrayList())
    }

    @Provides
    @Singleton
    internal fun provideSimilarMovieAdapterListner(listener: SimilarMovieAdapter.SimilarAdapterListener): SimilarMovieAdapter.SimilarAdapterListener {
        return listener
    }

   @Provides
    internal fun detailFragmentViewModelProvider(mainViewModel: DetailFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

    @Provides
    @Singleton
    internal fun provideSimilarAdapterListener(listener: SimilarMovieAdapter.SimilarAdapterListener): SimilarMovieAdapter.SimilarAdapterListener {
        return listener
    }

}