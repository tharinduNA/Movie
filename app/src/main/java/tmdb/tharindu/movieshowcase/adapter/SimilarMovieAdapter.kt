package tmdb.tharindu.movieshowcase.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import tmdb.tharindu.movieshowcase.databinding.ItemMovieSelectedBinding
import tmdb.tharindu.movieshowcase.databinding.ItemSimilarViewBinding
import tmdb.tharindu.movieshowcase.model.Movie
import tmdb.tharindu.movieshowcase.viewmodel.SimilarItemViewModel
import javax.inject.Inject


class SimilarMovieAdapter(var similarList: MutableList<Movie>) : RecyclerView.Adapter<BaseViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    @Inject
    lateinit var mListener: SimilarAdapterListener

    override fun getItemCount(): Int {
        return if (similarList.size > 0) {
            similarList.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        when (viewType) {
            TYPE_HEADER -> {
                val similarViewBinding = ItemMovieSelectedBinding.inflate(LayoutInflater.from(parent.context),
                        parent, false)
                return SelectedMovieViewHolder(similarViewBinding)
            }
            else -> {
                val similarViewBinding = ItemSimilarViewBinding.inflate(LayoutInflater.from(parent.context),
                        parent, false)
                return SimilarMovieViewHolder(similarViewBinding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_HEADER
        else
            return TYPE_ITEM
    }

    fun addItems(mList: List<Movie>) {
        similarList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        similarList.clear()
    }


    interface SimilarAdapterListener {
        fun onItemClick(similarMovie: Movie)
    }

    inner class SimilarMovieViewHolder(val binding: ItemSimilarViewBinding) :
            BaseViewHolder(binding.getRoot()), SimilarItemViewModel.SimilarItemViewModelListener {

        override fun onBind(position: Int) {
            val similar = similarList[position]
            val movieItemViewModel = SimilarItemViewModel(similar, this)
            binding.viewModel = movieItemViewModel
            binding.executePendingBindings()
        }

        override fun onItemClick(movie: Movie) {
            mListener.onItemClick(movie)
        }
    }

    inner class SelectedMovieViewHolder(val binding: ItemMovieSelectedBinding) : BaseViewHolder(binding.getRoot()) {

        override fun onBind(position: Int) {
            val similar = similarList[position]
            val similarItemViewModel = SimilarItemViewModel(similar, null)
            binding.viewModel = similarItemViewModel
            binding.executePendingBindings()
        }
    }

}
