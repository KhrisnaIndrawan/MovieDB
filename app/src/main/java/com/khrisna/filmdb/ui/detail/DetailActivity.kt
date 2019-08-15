package com.khrisna.filmdb.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import com.khrisna.filmdb.BuildConfig.BASE_IMG_URL
import com.khrisna.filmdb.R
import com.khrisna.filmdb.data.source.local.entity.FavoriteEntity
import com.khrisna.filmdb.data.source.local.entity.GenreEntity
import com.khrisna.filmdb.data.source.local.entity.MovieEntity
import com.khrisna.filmdb.data.source.local.entity.TVShowEntity
import com.khrisna.filmdb.data.source.vo.Status
import com.khrisna.filmdb.di.Injection
import com.khrisna.filmdb.utils.GlideApp
import com.khrisna.filmdb.utils.Utils.formatDate
import com.khrisna.filmdb.viewmodel.DetailViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private var isMovie: Boolean = false
    private var isFavorite: Boolean = false
    private lateinit var favorite: FavoriteEntity
    private lateinit var poster: String
    private lateinit var detailViewModel: DetailViewModel
    private var menuItem: Menu? = null

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

        intent.let {
            isMovie = it.getBooleanExtra(EXTRA_IS_MOVIE, false)
            poster = it.getStringExtra(EXTRA_POSTER) as String
            GlideApp.with(this)
                .load(BASE_IMG_URL + poster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(img_poster)

            val id: Int = it.getIntExtra(EXTRA_DETAIL_DATA, 0)
            favorite = FavoriteEntity(
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

        detailViewModel.movie?.observe(this, Observer { movie ->
            when (movie.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(
                        this,
                        "Get movie fail, please check your internet connection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Status.SUCCESS -> {
                    if (movie.data != null) {

                        val data = movie.data as MovieEntity

                        GlideApp.with(this)
                            .load(BASE_IMG_URL + data.backdrop)
                            .into(img_backdrop)

                        tv_title.text = data.title
                        tv_release_date.text = data.releaseDate?.let { formatDate(it) }
                        tv_rating.text = data.rating.toString()
                        tv_overview_body.text = data.overview
                        ratingBar.numStars = 5
                        ratingBar.stepSize = 0.1f
                        val rating: Float = data.rating ?: 0f
                        if (rating > 5f) {
                            ratingBar.rating = rating - 5
                        } else {
                            ratingBar.rating = rating
                        }

                        var genres = "| "
                        val movieGenres = data.genres as MutableList<GenreEntity>
                        for (value in movieGenres) {
                            genres += "${value.name} | "
                        }
                        tv_genres.text = genres

                        setViewVisible(true)
                    }
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showTVShowData() {

        detailViewModel.tvShow?.observe(this, Observer { tvShow ->
            when (tvShow.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(
                        this,
                        "Get tv show fail, please check your internet connection!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Status.SUCCESS -> {
                    if (tvShow.data != null) {

                        val data = tvShow.data as TVShowEntity

                        GlideApp.with(this)
                            .load(BASE_IMG_URL + data.backdrop)
                            .into(img_backdrop)

                        tv_title.text = data.title
                        tv_release_date_text.text = "First air date: "
                        tv_release_date.text = data.firstAir?.let { formatDate(it) }
                        tv_rating.text = data.rating.toString()
                        tv_overview_body.text = data.overview
                        ratingBar.numStars = 5
                        ratingBar.stepSize = 0.1f
                        val rating: Float = data.rating ?: 0f
                        if (rating > 5f) {
                            ratingBar.rating = rating - 5
                        } else {
                            ratingBar.rating = rating
                        }

                        var genres = "| "
                        val tvShowGenres = data.genres as MutableList<GenreEntity>
                        for (value in tvShowGenres) {
                            genres += "${value.name} | "
                        }
                        tv_genres.text = genres

                        setViewVisible(true)
                    }
                }
            }
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory
            .getInstance(Injection.provideRepository(activity.application))

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }

        return super.onOptionsItemSelected(item as MenuItem)
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
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_checked_24dp)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_unchecked_24dp)
        }
    }
}
