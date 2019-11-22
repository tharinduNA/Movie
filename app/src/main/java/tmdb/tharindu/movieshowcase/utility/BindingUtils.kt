package tmdb.tharindu.movieshowcase.utility

import tmdb.tharindu.movieshowcase.model.Movie
import tmdb.tharindu.movieshowcase.adapter.SimilarMovieAdapter
import tmdb.tharindu.movieshowcase.adapter.MovieAdapter
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("movieAdapter")
fun addMovieItems(recyclerView: RecyclerView, movies: List<Movie>) {
    val adapter = recyclerView.adapter as? MovieAdapter
    adapter?.clearItems()
    adapter?.addItems(movies)
}

@BindingAdapter("similarMovieAdapter")
fun addSimilarItems(recyclerView: RecyclerView, movies: List<Movie>) {
    val adapter = recyclerView.adapter as? SimilarMovieAdapter
    adapter?.clearItems()
    adapter?.addItems(movies)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val context = imageView.context
    Glide.with(context).load("http://image.tmdb.org/t/p/w185$url").into(imageView)
}

