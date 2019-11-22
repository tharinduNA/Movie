package tmdb.tharindu.movieshowcase.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import tmdb.tharindu.movieshowcase.databinding.ItemMovieViewBinding
import tmdb.tharindu.movieshowcase.model.Movie
import tmdb.tharindu.movieshowcase.utility.OnBottomReachedListener
import tmdb.tharindu.movieshowcase.viewmodel.MovieItemViewModel
import javax.inject.Inject

class MovieAdapter(var moviesList: MutableList<Movie>) : RecyclerView.Adapter<BaseViewHolder>() {

    @Inject
    lateinit var listener: MovieAdapterListener

    @Inject
    lateinit var onBottomReachedListener: OnBottomReachedListener

    override fun getItemCount(): Int {
        return if (moviesList.size > 0)
            moviesList.size
        else
            0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (position == moviesList.size - 1)
            onBottomReachedListener.onBottomReached(position)

        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val movieViewBinding = ItemMovieViewBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return MovieViewHolder(movieViewBinding)
    }

    fun addItems(mList: List<Movie>) {
        moviesList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        moviesList.clear()
    }

    interface MovieAdapterListener {
        fun onItemClick(movie: Movie)
    }

    inner class MovieViewHolder(val binding: ItemMovieViewBinding) : BaseViewHolder(binding.getRoot()), MovieItemViewModel.MovieItemViewModelListener {

        override fun onBind(position: Int) {
            val movie = moviesList[position]
            val movieItemViewModel = MovieItemViewModel(movie, this)
            binding.viewModel = movieItemViewModel
            binding.executePendingBindings()
        }

        override fun onItemClick(movie: Movie) {
            listener.onItemClick(movie)
        }
    }
}
