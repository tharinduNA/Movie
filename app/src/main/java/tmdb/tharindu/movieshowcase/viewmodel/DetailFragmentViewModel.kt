package tmdb.tharindu.movieshowcase.viewmodel

import tmdb.tharindu.movieshowcase.service.AppRepository
import tmdb.tharindu.movieshowcase.model.Movie
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor (appRepository: AppRepository) : BaseViewModel(appRepository) {

    private lateinit var movie: Movie

    var similarObservableList: ObservableArrayList<Movie> = ObservableArrayList<Movie>()

    var similarLiveData: MutableLiveData<MutableList<Movie>> = MutableLiveData()

    var title: ObservableField<String> = ObservableField()

    var overview: ObservableField<String> = ObservableField()

    var releaseDate: ObservableField<String> = ObservableField()

    var imageUrl: ObservableField<String>? = ObservableField()



    fun setMovie(mMovie: Movie) {
        movie = mMovie
        title.set(movie.title)
        overview.set(movie.overview)
        releaseDate.set(movie.release_date)
        imageUrl?.set(movie.poster_path)
        getSimilarMovies()
    }


    fun getSimilarMovies() {
        isLoading.set(true)
        val disposable = repository
                .getSimilarMovies(movieId = movie.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it?.results != null) {
                        similarLiveData.value = it.results
                    }
                    isLoading.set(false)
                }, { throwable ->
                    Log.v("error", throwable.toString())
                    isLoading.set(false)
                })
        compositeDisposable.add(disposable)
    }

    fun addSimilarMoviesToList(movies: MutableList<Movie>) {
        movies.add(0, movie)
        similarObservableList.clear()
        similarObservableList.addAll(movies)
    }


}

