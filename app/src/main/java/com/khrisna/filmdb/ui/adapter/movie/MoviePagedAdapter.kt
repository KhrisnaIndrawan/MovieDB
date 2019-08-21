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
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.khrisna.filmdb.BuildConfig
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.ui.detail.DetailActivity
import com.khrisna.filmdb.utils.GlideApp

class MoviePagedAdapter(
    private val context: Context
) : PagedListAdapter<MovieEntity, MoviePagedAdapter.MovieViewHolder>(
    object : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poster_small, parent, false))


    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPoster = view.findViewById<ImageView>(R.id.img_poster)

        fun bind(item: MovieEntity) {

            imgPoster.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                val imageViewPair = Pair.create<View, String>(imgPoster, "poster")
                val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as AppCompatActivity,
                        imageViewPair
                    )
                intent.putExtra(DetailActivity.EXTRA_DETAIL_DATA, item.id)
                intent.putExtra(DetailActivity.EXTRA_POSTER, item.poster)
                intent.putExtra(DetailActivity.EXTRA_IS_MOVIE, true)

                it.context.startActivity(intent, options.toBundle())
            }

            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            GlideApp.with(context)
                .load(BuildConfig.BASE_IMG_URL + item.poster)
                .apply(RequestOptions.placeholderOf(circularProgressDrawable).error(R.drawable.ic_error))
                .into(imgPoster)
        }
    }
}