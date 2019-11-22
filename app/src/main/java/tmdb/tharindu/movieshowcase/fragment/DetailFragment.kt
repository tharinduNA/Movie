package tmdb.tharindu.movieshowcase.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import tmdb.tharindu.movieshowcase.BR
import tmdb.tharindu.movieshowcase.R
import tmdb.tharindu.movieshowcase.adapter.SimilarMovieAdapter
import tmdb.tharindu.movieshowcase.databinding.FragmentSimilarBinding
import tmdb.tharindu.movieshowcase.model.Movie
import tmdb.tharindu.movieshowcase.utility.GridSpacingItemDecoration
import tmdb.tharindu.movieshowcase.viewmodel.DetailFragmentViewModel
import javax.inject.Inject

@SuppressLint("ValidFragment")
class DetailFragment(val movie: Movie) : BaseFragment<FragmentSimilarBinding, DetailFragmentViewModel>() , SimilarMovieAdapter.SimilarAdapterListener{

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var mSimilarMovieAdapter: SimilarMovieAdapter

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    lateinit var mDetailFragmentViewModel: DetailFragmentViewModel
    private lateinit var mFragmentDetailBinding: FragmentSimilarBinding
    lateinit var mListener: DetailFragmentListener

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_similar

    override fun getViewModel(): DetailFragmentViewModel {
        mDetailFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailFragmentViewModel::class.java)
        return mDetailFragmentViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSimilarMovieAdapter.mListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentDetailBinding = getViewDataBinding()
        setUp()
        subscribeToLiveData()
        mDetailFragmentViewModel.setMovie(movie)
    }

    private fun setUp() {
        gridLayoutManager.reverseLayout = false
        gridLayoutManager.isItemPrefetchEnabled = false
        mFragmentDetailBinding.similarMovieRecycler.setHasFixedSize(true)
        mFragmentDetailBinding.similarMovieRecycler.layoutManager = gridLayoutManager
        mFragmentDetailBinding.similarMovieRecycler.addItemDecoration(mGridSpacingItemDecoration)
        mFragmentDetailBinding.similarMovieRecycler.itemAnimator = DefaultItemAnimator()
        mFragmentDetailBinding.similarMovieRecycler.adapter = mSimilarMovieAdapter
    }

    private fun subscribeToLiveData() {
        mDetailFragmentViewModel.similarLiveData.observe(this, Observer {mDetailFragmentViewModel.addSimilarMoviesToList(it!!)} )
    }

    override fun onItemClick(movie: Movie) {
        mListener.onSimilarMovieSelected(movie)
    }

    interface DetailFragmentListener{
        fun onSimilarMovieSelected(selectedSimilar: Movie)
    }

}