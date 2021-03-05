package com.khrisna.favorite.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.khrisna.filmdb.R

class FavoriteSectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    private val tabs = arrayOf(
        R.string.tab_movies,
        R.string.tab_tv_shows
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> com.khrisna.favorite.ui.FavoriteMoviesFragment.newInstance()
            else -> com.khrisna.favorite.ui.FavoriteTVShowsFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabs[position])
    }

    override fun getCount(): Int = 2
}