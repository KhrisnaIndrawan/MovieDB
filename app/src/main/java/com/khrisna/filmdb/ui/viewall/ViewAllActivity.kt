package com.khrisna.filmdb.ui.viewall

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.khrisna.filmdb.databinding.ActivityViewAllBinding
import com.khrisna.filmdb.ui.adapter.movie.MovieAdapter
import com.khrisna.filmdb.ui.adapter.tvshow.TVShowAdapter
import com.khrisna.filmdb.viewmodel.ViewAllViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ViewAllActivity : AppCompatActivity() {

    private val viewAllViewModel: ViewAllViewModel by viewModel()
    private lateinit var movieListAdapter: MovieAdapter
    private lateinit var tvShowListAdapter: TVShowAdapter
    private var isMovie: Boolean = false
    private lateinit var header: String
    private lateinit var binding: ActivityViewAllBinding

    companion object {
        const val EXTRA_IS_MOVIE = "extra_movie"
        const val EXTRA_HEADER = "extra_header"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

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
            movieListAdapter = MovieAdapter(this, true)

            binding.rvViewall.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(context, 3)
                adapter = movieListAdapter
            }

            showMoviesData()
        } else {
            tvShowListAdapter = TVShowAdapter(this, true)

            binding.rvViewall.apply {
                setHasFixedSize(true)
                layoutManager =
                    GridLayoutManager(context, 3)
                adapter = tvShowListAdapter
            }

            showTvShowsData()
        }
    }

    private fun showMoviesData() {

        viewAllViewModel.getMovies(header).observe(this, Observer { data ->
            data.let {

                movieListAdapter.submitList(it.movies)
                movieListAdapter.notifyDataSetChanged()
            }

            binding.progressBar.visibility = View.INVISIBLE
        })
    }

    private fun showTvShowsData() {

        viewAllViewModel.getTVShows(header).observe(this, Observer { data ->
            data.let {

                tvShowListAdapter.submitList(it.tvShows)
                tvShowListAdapter.notifyDataSetChanged()
            }

            binding.progressBar.visibility = View.INVISIBLE
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            //to reverse the scene transition animation
            supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }
}
