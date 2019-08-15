package com.khrisna.filmdb.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.khrisna.filmdb.R
import com.khrisna.filmdb.ui.movies.MoviesFragment
import com.khrisna.filmdb.ui.tvshows.TVShowsFragment

class HomeSectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val tabs = arrayOf(
        R.string.tab_movies,
        R.string.tab_tv_shows
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment.newInstance()
            else -> TVShowsFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabs[position])
    }

    override fun getCount(): Int = 2
}