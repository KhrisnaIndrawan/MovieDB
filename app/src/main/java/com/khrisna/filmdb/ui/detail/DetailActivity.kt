package com.khrisna.filmdb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import com.khrisna.filmdb.BuildConfig.BASE_IMG_URL
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.GenreEntity
import com.khrisna.filmdb.di.Injection
import com.khrisna.filmdb.utils.GlideApp
import com.khrisna.filmdb.utils.Utils.formatDate
import com.khrisna.filmdb.viewmodel.DetailViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private var isMovie: Boolean = false
    private lateinit var poster: String
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val EXTRA_DETAIL_DATA = "extra_detail_data"
        const val EXTRA_IS_MOVIE = "extra_movie"
        const val EXTRA_POSTER = "extra_poster"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailViewModel = obtainViewModel(this)

        if (intent != null) {
            isMovie = intent.getBooleanExtra(EXTRA_IS_MOVIE, false)
            poster = intent.getStringExtra(EXTRA_POSTER) as String
            GlideApp.with(this)
                .load(BASE_IMG_URL + poster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(img_poster)

            if (isMovie) {
                supportActionBar.let {
                    title = "Movie Details"
                }
                val movie: String = intent.getStringExtra(EXTRA_DETAIL_DATA) as String
                if (detailViewModel.movie == null) {
                    detailViewModel.getMovie(movie)
                }
                showMovieData()
            } else {
                supportActionBar.let {
                    title = "TVShow Details"
                }
                val tvShow: String = intent.getStringExtra(EXTRA_DETAIL_DATA) as String
                if (detailViewModel.tvShow == null) {
                    detailViewModel.getTVShow(tvShow)
                }
                showTVShowData()
            }
        }
    }

    private fun showMovieData() {

        detailViewModel.movie?.observe(this, Observer { movie ->
            setViewVisible(true)

            GlideApp.with(this)
                .load(BASE_IMG_URL + movie.backdrop)
                .into(img_backdrop)

            tv_title.text = movie.title
            tv_release_date.text = movie.releaseDate?.let { formatDate(it) }
            tv_rating.text = movie.rating.toString()
            tv_overview_body.text = movie.overview
            ratingBar.numStars = 5
            ratingBar.stepSize = 0.1f
            val rating: Float = movie.rating ?: 0f
            if (rating > 5f) {
                ratingBar.rating = rating - 5
            } else {
                ratingBar.rating = rating
            }

            var genres = "| "
            val movieGenres = movie.genres as MutableList<GenreEntity>
            for ((index, value) in movieGenres.withIndex()) {
                genres += "${value.name} | "
                if (index == 2) {
                    genres += "\n"
                }
            }
            tv_genres.text = genres
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showTVShowData() {

        detailViewModel.tvShow?.observe(this, Observer { tvShow ->
            setViewVisible(true)

            GlideApp.with(this)
                .load(BASE_IMG_URL + tvShow.backdrop)
                .into(img_backdrop)

            tv_title.text = tvShow.title
            tv_release_date_text.text = "First air date: "
            tv_release_date.text = tvShow.firstAir?.let { formatDate(it) }
            tv_rating.text = tvShow.rating.toString()
            tv_overview_body.text = tvShow.overview
            ratingBar.numStars = 5
            ratingBar.stepSize = 0.1f
            val rating: Float = tvShow.rating ?: 0f
            if (rating > 5f) {
                ratingBar.rating = rating - 5
            } else {
                ratingBar.rating = rating
            }

            var genres = "| "
            val tvShowGenres = tvShow.genres as MutableList<GenreEntity>
            for ((index, value) in tvShowGenres.withIndex()) {
                genres += "${value.name} | "
                if (index == 2) {
                    genres += "\n"
                }
            }
            tv_genres.text = genres
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(Injection.provideRepository())

        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

    private fun setViewVisible(visibility: Boolean) {
        if (visibility) {
            cv_backdrop.visibility = View.VISIBLE
            img_backdrop.visibility = View.VISIBLE
            ratingBar.visibility = View.VISIBLE
            tv_release_date_text.visibility = View.VISIBLE
            tv_overview.visibility = View.VISIBLE
            tv_per_score.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            //to reverse the scene transition animation
            supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }
}
