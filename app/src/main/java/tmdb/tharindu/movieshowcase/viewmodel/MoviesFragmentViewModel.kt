package tmdb.tharindu.movieshowcase.viewmodel

import tmdb.tharindu.movieshowcase.service.AppRepository
import tmdb.tharindu.movieshowcase.model.Movie
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesFragmentViewModel @Inject constructor (appRepository: AppRepository) : BaseViewModel(appRepository) {

    var movieObservableList: ObservableList<Movie> = ObservableArrayList<Movie>()

    var movieListLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    var i = 1;
    var totalPages = 999

    init {
        fetchMovies()
    }

    fun addMovieItemsToList(movies: List<Movie>) {
        movieObservableList.addAll(movies)
    }

    fun fetchMovies() {

        if (i >= totalPages) return
        isLoading.set(true)
        compositeDisposable.add(repository
                .getMovies(i.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieResponse ->

                    totalPages = movieResponse.total_pages.toInt()

                    if (movieResponse?.results != null) {
                        movieListLiveData.setValue(movieResponse.results)
                    }
                    i++
                    isLoading.set(false)

                }, { throwable ->
                    isLoading.set(false)
                }))
    }

}