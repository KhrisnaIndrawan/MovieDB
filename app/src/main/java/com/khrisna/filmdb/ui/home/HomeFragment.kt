package com.khrisna.filmdb.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.khrisna.filmdb.R
import com.khrisna.filmdb.ui.adapter.home.HomeSectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val sectionsPagerAdapter = HomeSectionsPagerAdapter(
            context as AppCompatActivity,
            childFragmentManager
        )
        view.view_pager.adapter = sectionsPagerAdapter
        view.tabs.setupWithViewPager(view.view_pager)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}
