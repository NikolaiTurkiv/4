package com.test.a4.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.a4.R
import com.test.a4.databinding.FragmentFixturesBinding
import com.test.a4.databinding.FragmentHomeBinding
import com.test.a4.ui.adapters.Delegate
import com.test.a4.ui.viewmodels.FixturesViewModel
import com.test.a4.ui.viewmodels.HomeViewModel
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixturesFragment : Fragment(R.layout.fragment_fixtures) {

    private val binding by viewBinding<FragmentFixturesBinding>()
    private val viewModel by viewModels<FixturesViewModel>()

    private val adapter = ListDelegationAdapter(
        Delegate.fixturesAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFixtures.adapter = adapter
        initObserver()
    }

    private fun initObserver() {
        viewModel.nameLD.observe(viewLifecycleOwner){
            Log.d("TEAMS", it)
            viewModel.getFixtures(it)
        }

        viewModel.fixturesLD.observe(viewLifecycleOwner) {
            adapter.apply {
                items = it
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FixturesFragment()
    }
}