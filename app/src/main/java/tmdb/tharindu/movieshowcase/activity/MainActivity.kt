package tmdb.tharindu.movieshowcase.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import tmdb.tharindu.movieshowcase.BR
import tmdb.tharindu.movieshowcase.R
import tmdb.tharindu.movieshowcase.databinding.ActivityMainBinding
import tmdb.tharindu.movieshowcase.fragment.DetailFragment
import tmdb.tharindu.movieshowcase.fragment.MoviesFragment
import tmdb.tharindu.movieshowcase.model.Movie
import tmdb.tharindu.movieshowcase.utility.AppConstants
import tmdb.tharindu.movieshowcase.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
        MoviesFragment.MainFragmentListener, DetailFragment.DetailFragmentListener {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var mActivityMainBinding: ActivityMainBinding

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        val mainFragment = MoviesFragment()
        mainFragment.listener = this
        replaceFragment(mainFragment, AppConstants.HOME_FRAGMENT)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        if (fragment is DetailFragment)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag)
                    .addToBackStack(tag).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag)
                    .commit()

    }

    override fun onMovieSelected(movie: Movie) {
        val detailFragment = DetailFragment(movie)
        detailFragment.mListener = this
        replaceFragment(detailFragment, AppConstants.DETAIL_FRAGMENT)
    }

    override fun onBackPressed() {
        val fragment = getSupportFragmentManager().findFragmentByTag(AppConstants.HOME_FRAGMENT);
        if(fragment!=null && fragment.isVisible())
            finish()
        else
            setUp()
    }

    override fun onSimilarMovieSelected(movie: Movie){
        onMovieSelected(movie)
    }


}

