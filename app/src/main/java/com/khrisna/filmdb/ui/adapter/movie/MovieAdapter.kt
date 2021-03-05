package com.khrisna.filmdb.ui.adapter.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.khrisna.core.BuildConfig.BASE_IMG_URL
import com.khrisna.core.domain.model.Movie
import com.khrisna.core.utils.GlideApp
import com.khrisna.filmdb.R
import com.khrisna.filmdb.ui.detail.DetailActivity
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_DETAIL_DATA
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_IS_MOVIE
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_POSTER

class MovieAdapter(
    private val context: Context,
    private val small: Boolean
) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return when (small) {
            false -> MovieViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false)
            )
            else -> MovieViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_poster_small, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPoster = view.findViewById<ImageView>(R.id.img_poster)

        fun bind(item: Movie) {

            imgPoster.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                val imageViewPair = Pair.create<View, String>(imgPoster, "poster")
                val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as AppCompatActivity,
                        imageViewPair
                    )
                intent.putExtra(EXTRA_DETAIL_DATA, item.id)
                intent.putExtra(EXTRA_POSTER, item.poster)
                intent.putExtra(EXTRA_IS_MOVIE, true)

                it.context.startActivity(intent, options.toBundle())
            }

            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            GlideApp.with(context)
                .load(BASE_IMG_URL + item.poster)
                .apply(
                    RequestOptions.placeholderOf(circularProgressDrawable)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)
        }
    }
}