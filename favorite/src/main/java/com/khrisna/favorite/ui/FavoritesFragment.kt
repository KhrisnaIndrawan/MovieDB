package com.khrisna.favorite.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.khrisna.favorite.R
import com.khrisna.favorite.ui.adapter.FavoriteSectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorites.view.*


class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(
            context as AppCompatActivity,
            childFragmentManager
        )
        view.view_pager.adapter = sectionsPagerAdapter
        view.tabs.setupWithViewPager(view.view_pager)

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }
}