package com.khrisna.filmdb.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.ActivityHomeBinding
import com.khrisna.filmdb.ui.movies.MoviesFragment
import com.khrisna.filmdb.ui.search.SearchFragment
import com.khrisna.filmdb.ui.tvshows.TVShowsFragment

private const val NUM_PAGES = 4

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        setContentView(binding.root)

        val pagerAdapter = SlidePagerAdapter(this)
        binding.fragmentContainer.adapter = pagerAdapter

        binding.fragmentContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bubbleTabBar.setSelected(position)
            }
        })

        binding.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.app_bar_movie -> {
                    binding.fragmentContainer.currentItem = 0
                }
                R.id.app_bar_tvshow -> {
                    binding.fragmentContainer.currentItem = 1
                }
                R.id.app_bar_favorite -> {
                    binding.fragmentContainer.currentItem = 2
                }
                R.id.app_bar_search -> {
                    binding.fragmentContainer.currentItem = 3
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.fragmentContainer.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.fragmentContainer.currentItem = binding.fragmentContainer.currentItem - 1
        }
    }

    private inner class SlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> {
                MoviesFragment.newInstance()
            }
            1 -> {
                TVShowsFragment.newInstance()
            }
            2 -> {
                Class.forName("com.khrisna.favorite.ui.FavoritesFragment").newInstance() as Fragment
            }
            3 -> {
                SearchFragment.newInstance()
            }
            else -> MoviesFragment.newInstance()
        }
    }
}