package com.khrisna.filmdb.ui.pagelist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.khrisna.filmdb.R
import com.khrisna.filmdb.di.Injection
import com.khrisna.filmdb.ui.adapter.MovieAdapter
import com.khrisna.filmdb.ui.adapter.TVShowAdapter
import com.khrisna.filmdb.viewmodel.ViewAllViewModel
import com.khrisna.filmdb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_view_all.*

class ViewAllActivity : AppCompatActivity() {

    private lateinit var viewAllViewModel: ViewAllViewModel
    private lateinit var movieListAdapter: MovieAdapter
    private lateinit var tvShowListAdapter: TVShowAdapter
    private var isMovie: Boolean = false
    private lateinit var header: String
    private var page: String = "1"

    companion object {
        const val EXTRA_IS_MOVIE = "extra_movie"
        const val EXTRA_HEADER = "extra_header"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        viewAllViewModel = obtainViewModel(this)

        if (intent != null) {
            isMovie = intent.getBooleanExtra(EXTRA_IS_MOVIE, false)
            header = intent.getStringExtra(EXTRA_HEADER) as String
            supportActionBar?.apply {
                title = if (isMovie) {
                    "All $header Movies"
                } else {
                    "All $header TVShows"
                }
            }
        }

        if (isMovie) {
            movieListAdapter = MovieAdapter(this)

            rv_view_all.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(context, 3)
                adapter = movieListAdapter
            }

            showMoviesData()
        } else {
            tvShowListAdapter = TVShowAdapter(this)

            rv_view_all.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(context, 3)
                adapter = tvShowListAdapter
            }

            showTvShowsData()
        }
    }

    private fun showMoviesData() {
        viewAllViewModel.getMovies(header, page)

        viewAllViewModel.movies?.observe(this, Observer { data ->
            if (data != null) {
                movieListAdapter.submitList(data.movies)
            }

            progressBar.visibility = View.INVISIBLE
        })
    }

    private fun showTvShowsData() {
        viewAllViewModel.getTVShows(header, page)

        viewAllViewModel.tvShows?.observe(this, Observer { data ->
            if (data != null) {
                tvShowListAdapter.submitList(data.tvShow)
            }

            progressBar.visibility = View.INVISIBLE
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): ViewAllViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory(Injection.provideRepository())

        return ViewModelProviders.of(activity, factory).get(ViewAllViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            //to reverse the scene transition animation
            supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }
}
