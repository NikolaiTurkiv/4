package com.test.a4.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.a4.ui.screens.*

class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.newInstance()
            1-> FixturesFragment.newInstance()
            2->ResultsFragment.newInstance()
            3->LeagueFragment.newInstance()
            else -> SquadFragment.newInstance()
        }
    }

}