package com.khrisna.filmdb.ui.movies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khrisna.filmdb.R
import com.khrisna.filmdb.adapter.MovieListAdapter
import com.khrisna.filmdb.data.model.Movies
import com.khrisna.filmdb.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {

    private lateinit var model: MoviesViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movies: MutableList<Movies>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            model = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
            if (model.nowPlaying == null) {
                model.getNowPlaying()
            }
            model.nowPlaying?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
            if (model.upComing == null) {
                model.getUpComing()
            }
            model.upComing?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
            if (model.popular == null) {
                model.getPopular()
            }
            model.popular?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
            if (model.topRated == null) {
                model.getTopRate()
            }
            model.topRated?.observe(this, Observer { data ->
                if (data != null) {
                    movies.add(data)
                    movieListAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies = mutableListOf()
        movieListAdapter = MovieListAdapter(context as AppCompatActivity, movies)

        rv_movies.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context as AppCompatActivity, RecyclerView.VERTICAL, false)
            adapter = movieListAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}
