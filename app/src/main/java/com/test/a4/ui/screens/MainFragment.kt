package com.test.a4.ui.screens

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.test.a4.R
import com.test.a4.databinding.FragmentMainBinding
import com.test.a4.databinding.FragmentSearchBinding
import com.test.a4.ui.adapters.PagerAdapter
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductDetailsViewPager()
    }

    private fun initProductDetailsViewPager() {
        val adapter = PagerAdapter(requireActivity())
        binding.vpMain.adapter = adapter
        TabLayoutMediator(binding.tlMain, binding.vpMain) { tab, pos ->
            when (pos) {
                0 -> tab.text = getString(R.string.home)
                1 -> tab.text = getString(R.string.fixtures)
                2 -> tab.text = getString(R.string.results)
                3 -> tab.text = getString(R.string.league)
                else -> tab.text = getString(R.string.squad)
            }
        }.attach()

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}