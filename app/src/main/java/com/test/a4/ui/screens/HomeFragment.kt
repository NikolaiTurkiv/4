package com.test.a4.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.a4.R
import com.test.a4.databinding.FragmentHomeBinding
import com.test.a4.ui.viewmodels.HomeViewModel
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.nameLD.observe(viewLifecycleOwner){
            Log.d("TEAMS", it)
            viewModel.getClubInfo(it)
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.clubInfoLD.observe(viewLifecycleOwner) {
            val item = it[0]
            with(binding) {
                tvCapacity.text = item.capacity
                tvChairman.text = item.chairman
                tvFounded.text = item.founded
                tvManager.text = item.manager
                tvFullName.text = item.full_name
                tvNickNames.text = item.nicknames
                tvVenue.text = item.venue_name
                tvWebsite.text = item.website
                tvTeamName.text = item.name
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}