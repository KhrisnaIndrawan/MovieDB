package com.khrisna.filmdb.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.ActivityHomeBinding
import com.khrisna.filmdb.ui.movies.MoviesFragment
import com.khrisna.filmdb.ui.search.SearchFragment
import com.khrisna.filmdb.ui.tvshows.TVShowsFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        if (savedInstanceState == null) {
            showFragment(MoviesFragment.newInstance())
        }

        binding.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.app_bar_movie -> {
                    showFragment(MoviesFragment.newInstance())
                }
                R.id.app_bar_tvshow -> {
                    showFragment(TVShowsFragment.newInstance())
                }
                R.id.app_bar_favorite -> {
                    showFragment(
                        Class.forName("com.khrisna.favorite.ui.FavoritesFragment")
                            .newInstance() as Fragment
                    )
                }
                R.id.app_bar_search -> {
                    showFragment(SearchFragment.newInstance())
                }
            }
        }
    }

    private fun showFragment(fragmentInstance: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                fragmentInstance
            )
            .commit()
    }
}