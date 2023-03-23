package com.test.a4.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.a4.R
import com.test.a4.databinding.FragmentResultsBinding
import com.test.a4.databinding.FragmentSquadBinding
import com.test.a4.ui.adapters.Delegate
import com.test.a4.ui.viewmodels.ResultsViewModel
import com.test.a4.ui.viewmodels.SquadViewModel
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SquadFragment : Fragment(R.layout.fragment_squad) {
    private val binding by viewBinding<FragmentSquadBinding>()
    private val viewModel by viewModels<SquadViewModel>()

    private val adapter = ListDelegationAdapter(
        Delegate.squadAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSquad.adapter = adapter
        initObserver()
    }

    private fun initObserver(){
        viewModel.nameLD.observe(viewLifecycleOwner){
            viewModel.getSquad(it)
        }

        viewModel.squadLD.observe(viewLifecycleOwner){
            adapter.apply {
                items = it
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SquadFragment()
    }
}