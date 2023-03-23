package com.test.a4.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.a4.R
import com.test.a4.databinding.FragmentResultsBinding
import com.test.a4.ui.adapters.Delegate
import com.test.a4.ui.viewmodels.FixturesViewModel
import com.test.a4.ui.viewmodels.ResultsViewModel
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultsFragment : Fragment(R.layout.fragment_results) {

    private val binding by viewBinding<FragmentResultsBinding>()
    private val viewModel by viewModels<ResultsViewModel>()

    private val adapter = ListDelegationAdapter(
        Delegate.resultAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvResult.adapter = adapter
        initObserver()
    }

    private fun initObserver(){
        viewModel.nameLD.observe(viewLifecycleOwner){
            Log.d("TEAMS", it)
            viewModel.getResults(it)
        }

        viewModel.resultsLD.observe(viewLifecycleOwner){
            adapter.apply {
                items = it
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultsFragment()
    }
}