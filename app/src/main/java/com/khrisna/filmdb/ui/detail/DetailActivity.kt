package com.khrisna.filmdb.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.request.RequestOptions
import com.khrisna.core.BuildConfig.BASE_IMG_URL
import com.khrisna.core.data.source.vo.Resource
import com.khrisna.core.domain.model.Favorite
import com.khrisna.core.domain.model.Genre
import com.khrisna.core.domain.model.Movie
import com.khrisna.core.domain.model.TVShow
import com.khrisna.core.utils.GlideApp
import com.khrisna.core.utils.Utils.formatDate
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.ActivityDetailBinding
import com.khrisna.filmdb.viewmodel.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    private var isMovie: Boolean = false
    private var isFavorite: Boolean = false
    private lateinit var favorite: Favorite
    private lateinit var poster: String
    private var menuItem: Menu? = null

    companion object {
        const val EXTRA_DETAIL_DATA = "extra_detail_data"
        const val EXTRA_IS_MOVIE = "extra_movie"
        const val EXTRA_POSTER = "extra_poster"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.let {
            isMovie = it.getBooleanExtra(EXTRA_IS_MOVIE, false)
            poster = it.getStringExtra(EXTRA_POSTER) as String
            GlideApp.with(this)
                .load(BASE_IMG_URL + poster)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(binding.imgPoster)

            val id: Int = it.getIntExtra(EXTRA_DETAIL_DATA, 0)
            favorite = Favorite(
                id = id,
                dataId = id,
                poster = poster,
                isMovie = isMovie
            )

            setFavoriteState(id)

            if (isMovie) {
                supportActionBar.let {
                    title = "Movie Details"
                }
                if (detailViewModel.movie == null) {
                    detailViewModel.getMovie(id)
                }

                showMovieData()
            } else {
                supportActionBar.let {
                    title = "TVShow Details"
                }

                if (detailViewModel.tvShow == null) {
                    detailViewModel.getTVShow(id)
                }

                showTVShowData()
            }
        }
    }

    private fun showMovieData() {

        detailViewModel.movie?.observe(this, { movie ->
            when (movie) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        "Get movie fail, please check your internet connection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Success -> {
                    if (movie.data != null) {

                        val data = movie.data as Movie

                        GlideApp.with(this)
                            .load(BASE_IMG_URL + data.backdrop)
                            .into(binding.imgBackdrop)

                        binding.tvTitle.text = data.title
                        binding.tvReleaseDate.text = data.releaseDate?.let { formatDate(it) }
                        binding.tvRating.text = data.rating.toString()
                        binding.tvOverviewBody.text = data.overview
                        binding.ratingBar.numStars = 5
                        binding.ratingBar.stepSize = 0.1f
                        val rating: Float = data.rating ?: 0f
                        if (rating > 5f) {
                            binding.ratingBar.rating = rating - 5
                        } else {
                            binding.ratingBar.rating = rating
                        }

                        var genres = "| "
                        data.genres.let {

                            for (value in it as List<Genre>) {
                                genres += "${value.name} | "
                            }
                            binding.tvGenre.text = genres
                        }

                        setViewVisible(true)
                    }
                }
            }
        })
    }

    private fun showTVShowData() {

        detailViewModel.tvShow?.observe(this, Observer { tvShow ->
            when (tvShow) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        "Get tv show fail, please check your internet connection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Success -> {
                    if (tvShow.data != null) {

                        val data = tvShow.data as TVShow

                        GlideApp.with(this)
                            .load(BASE_IMG_URL + data.backdrop)
                            .into(binding.imgBackdrop)

                        binding.tvTitle.text = data.title
                        binding.tvReleaseDateText.text = "First air date: "
                        binding.tvReleaseDate.text = data.firstAir?.let { formatDate(it) }
                        binding.tvRating.text = data.rating.toString()
                        binding.tvOverviewBody.text = data.overview
                        binding.ratingBar.numStars = 5
                        binding.ratingBar.stepSize = 0.1f
                        val rating: Float = data.rating ?: 0f
                        if (rating > 5f) {
                            binding.ratingBar.rating = rating - 5
                        } else {
                            binding.ratingBar.rating = rating
                        }

                        var genres = "| "
                        val tvShowGenres = data.genres as MutableList<Genre>
                        for (value in tvShowGenres) {
                            genres += "${value.name} | "
                        }
                        binding.tvGenre.text = genres

                        setViewVisible(true)
                    }
                }
            }
        })
    }

    private fun setViewVisible(visibility: Boolean) {
        if (visibility) {
            binding.cvBackdrop.visibility = View.VISIBLE
            binding.imgBackdrop.visibility = View.VISIBLE
            binding.ratingBar.visibility = View.VISIBLE
            binding.tvReleaseDateText.visibility = View.VISIBLE
            binding.tvOverview.visibility = View.VISIBLE
            binding.tvPerscore.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // to reverse the scene transition animation
        supportFinishAfterTransition()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(id: Int) {
        detailViewModel.getFavorite(id)
        detailViewModel.favorite?.observe(this, Observer { data ->
            if (data != null) {
                isFavorite = true
            }
        })
    }

    private fun removeFromFavorite() {
        detailViewModel.deleteFavorite(favorite)
        Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorite() {
        detailViewModel.insertFavorite(favorite)
        Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_checked_24dp)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_unchecked_24dp)
        }
    }
}
