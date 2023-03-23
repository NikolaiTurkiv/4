package com.test.a4.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.a4.R
import com.test.a4.databinding.FragmentLeagueBinding
import com.test.a4.ui.adapters.Delegate
import com.test.a4.ui.viewmodels.LeagueViewModel
import com.test.a4.ui.viewmodels.SquadViewModel
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueFragment : Fragment(R.layout.fragment_league) {

    private val binding by viewBinding<FragmentLeagueBinding>()
    private val viewModel by viewModels<LeagueViewModel>()

    private val adapter = ListDelegationAdapter(
        Delegate.tableTitleAdapter,
        Delegate.tableAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLeague.adapter = adapter
        viewModel.getTable()
        initObserver()
    }

    private fun initObserver(){
        viewModel.leagueLD.observe(viewLifecycleOwner){
            adapter.apply {
                items = it
                notifyDataSetChanged()
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = LeagueFragment()
    }
}