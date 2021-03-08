package com.khrisna.filmdb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.khrisna.filmdb.R
import com.khrisna.filmdb.databinding.FragmentSearchBinding
import com.khrisna.filmdb.ui.adapter.search.SearchAdapter
import com.khrisna.filmdb.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val model: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var progressBar: ProgressBar
    private var query: String = ""
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding as FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let { activity ->

            binding.btnSearch.setOnClickListener {
                query = binding.edtQuery.text.toString()

                model.getSearches(query).observe(viewLifecycleOwner, Observer { data ->

                    data.let {

                        searchAdapter.submitList(data.searches)
                        searchAdapter.notifyDataSetChanged()

                        progressBar.visibility = View.INVISIBLE
                    }
                })
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter(context as AppCompatActivity)

        progressBar = view.findViewById(R.id.progressBar)

        binding.rvSearch.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = searchAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
