package tmdb.tharindu.movieshowcase.viewmodel

import tmdb.tharindu.movieshowcase.model.Movie
import android.databinding.ObservableField

class MovieItemViewModel(var movie: Movie, var listener: MovieItemViewModelListener?) {

    var imageUrl = ObservableField(movie.poster_path)
    var title: ObservableField<String> = ObservableField(movie.title)

    fun onItemClick() {
        listener?.onItemClick(movie)
    }

    interface MovieItemViewModelListener {
        fun onItemClick(movie: Movie)
    }
}