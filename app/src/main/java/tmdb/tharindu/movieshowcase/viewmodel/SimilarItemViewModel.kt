package tmdb.tharindu.movieshowcase.viewmodel

import tmdb.tharindu.movieshowcase.model.Movie
import android.databinding.ObservableField

class SimilarItemViewModel (var movie: Movie, var listener: SimilarItemViewModelListener?)  {

    var name: ObservableField<String> = ObservableField(movie.title)
    var imageUrl: ObservableField<String> = ObservableField(movie.poster_path)
    var overview: ObservableField<String> = ObservableField(movie.overview)
    var release: ObservableField<String> = ObservableField(movie.release_date)

    fun onItemClick(){
        listener?.onItemClick(movie)
    }

    fun getReleaseYear(): String{
        return release.get()?.split("-")?.get(0) ?: ""
    }

    interface SimilarItemViewModelListener {
        fun onItemClick(movie: Movie)
    }
}