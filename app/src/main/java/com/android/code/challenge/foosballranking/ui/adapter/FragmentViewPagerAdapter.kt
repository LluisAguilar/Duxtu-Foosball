package com.android.code.challenge.foosballranking.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentViewPagerAdapter(private val mFragmentList: List<Fragment>, fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }
}