package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProviders.of(activity, MainViewModelFactory(activity.application))
                .get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = AsteroidAdapter(AsteroidListener { asteroid ->
            viewModel.onAsteroidClicked(asteroid)
        })

        binding.asteroidRecycler.adapter = adapter
        viewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            asteroidList?.let {
                adapter.submitList(asteroidList)
            }
        })

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { asteroid ->
            asteroid?.let {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.onAsteroidDetailNavigated()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.show_week_menu -> {
                viewModel.selectedFilter.value = Filter.WEEK
                true
            }
            R.id.show_today_menu -> {
                viewModel.selectedFilter.value = Filter.TODAY
                true
            }
            R.id.show_all_menu -> {
                viewModel.selectedFilter.value = Filter.ALL
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
