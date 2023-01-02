package com.go0ose.agedeterminant.presentation.mainScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.FavoriteFragment
import com.go0ose.agedeterminant.presentation.mainScreen.pages.search.SearchFragment

class PageAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            else -> FavoriteFragment()
        }
    }
}