package com.khrisna.filmdb.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.khrisna.filmdb.R
import com.khrisna.filmdb.ui.favorite.FavoritesFragment
import com.khrisna.filmdb.ui.home.HomeFragment
import com.khrisna.filmdb.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*


class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(bottom_app_bar)

        if (savedInstanceState == null) {
            showFragment(HomeFragment.newInstance())
        }

        fab.setOnClickListener {
            showFragment(SearchFragment.newInstance())
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_home -> {
                showFragment(HomeFragment.newInstance())
            }
            R.id.app_bar_favorite -> {
                showFragment(FavoritesFragment.newInstance())
            }
//            R.id.app_bar_translation -> Toast.makeText(
//                this, "Setting menu item is clicked!",
//                Toast.LENGTH_SHORT
//            ).show()
        }

        return true
    }
}