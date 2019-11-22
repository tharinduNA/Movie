package tmdb.tharindu.movieshowcase.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import tmdb.tharindu.movieshowcase.BR
import tmdb.tharindu.movieshowcase.R
import tmdb.tharindu.movieshowcase.adapter.MovieAdapter
import tmdb.tharindu.movieshowcase.databinding.FragmentMoviesBinding
import tmdb.tharindu.movieshowcase.model.Movie
import tmdb.tharindu.movieshowcase.utility.GridSpacingItemDecoration
import tmdb.tharindu.movieshowcase.utility.OnBottomReachedListener
import tmdb.tharindu.movieshowcase.viewmodel.MoviesFragmentViewModel
import javax.inject.Inject

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesFragmentViewModel>() , MovieAdapter.MovieAdapterListener{

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: GridLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter

    lateinit var mMoviesFragmentViewModel: MoviesFragmentViewModel

    private lateinit var fragmentMainBinding: FragmentMoviesBinding

    lateinit var listener: MainFragmentListener

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_movies

    override fun getViewModel(): MoviesFragmentViewModel {
        mMoviesFragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesFragmentViewModel::class.java)
        return mMoviesFragmentViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieAdapter.listener = this
        mMovieAdapter.onBottomReachedListener =  (object : OnBottomReachedListener {
            override fun onBottomReached(position: Int) {
                mMoviesFragmentViewModel.fetchMovies()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMainBinding = getViewDataBinding()
        setUp()
        subscribeToLiveData()
    }

    private fun setUp() {
        mGridLayoutManager.reverseLayout = false
        mGridLayoutManager.isItemPrefetchEnabled = false
        fragmentMainBinding.moviesRecycler.setHasFixedSize(true)
        fragmentMainBinding.moviesRecycler.layoutManager = mGridLayoutManager
        fragmentMainBinding.moviesRecycler.addItemDecoration(mGridSpacingItemDecoration)
        fragmentMainBinding.moviesRecycler.itemAnimator = DefaultItemAnimator()
        fragmentMainBinding.moviesRecycler.adapter = mMovieAdapter
    }

    private fun subscribeToLiveData() {
        mMoviesFragmentViewModel.movieListLiveData.observe(this, Observer {mMoviesFragmentViewModel.addMovieItemsToList(it!!)} )
    }

    override fun onItemClick(movie: Movie) {
        listener.onMovieSelected(movie)
    }

    interface MainFragmentListener{
        fun onMovieSelected(movie: Movie)
    }

}