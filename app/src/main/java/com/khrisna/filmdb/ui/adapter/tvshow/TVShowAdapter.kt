package com.khrisna.filmdb.ui.adapter.tvshow

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
import com.bumptech.glide.request.RequestOptions
import com.khrisna.filmdb.BuildConfig.BASE_IMG_URL
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.ui.detail.DetailActivity
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_DETAIL_DATA
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_IS_MOVIE
import com.khrisna.filmdb.ui.detail.DetailActivity.Companion.EXTRA_POSTER
import com.khrisna.filmdb.utils.GlideApp

class TVShowAdapter(private val context: Context) :
    ListAdapter<TVShowEntity, TVShowAdapter.TVShowViewHolder>(
        object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TVShowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_poster, parent, false))

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TVShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPoster = view.findViewById<ImageView>(R.id.img_poster)

        fun bind(item: TVShowEntity) {

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
                intent.putExtra(EXTRA_IS_MOVIE, false)

                it.context.startActivity(intent, options.toBundle())
            }

            GlideApp.with(context)
                .load(BASE_IMG_URL + item.poster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgPoster)
        }
    }
}