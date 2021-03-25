package com.khrisna.filmdb.ui.adapter.search

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
import com.khrisna.core.domain.model.Search
import com.khrisna.core.utils.GlideApp
import com.khrisna.filmdb.R
import com.khrisna.filmdb.ui.detail.DetailActivity
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_DETAIL_DATA
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_IS_MOVIE
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_POSTER

class SearchAdapter(private val context: Context) :
    ListAdapter<Search, SearchAdapter.SearchViewHolder>(
        object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_poster_small, parent, false)
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPoster = view.findViewById<ImageView>(R.id.img_poster)

        fun bind(item: Search) {

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
                .override(480)
                .into(imgPoster)
        }
    }
}