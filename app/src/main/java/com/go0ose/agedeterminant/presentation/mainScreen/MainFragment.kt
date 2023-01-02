package com.go0ose.agedeterminant.presentation.mainScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.go0ose.agedeterminant.AgeApplication
import com.go0ose.agedeterminant.R
import com.go0ose.agedeterminant.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import javax.annotation.Resource
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    @Inject
    lateinit var sharedViewModel: SharedViewModel

    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AgeApplication.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {

        binding.viewPager.adapter = PageAdapter(requireActivity())

        binding.tabLayout

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = getString(R.string.main)
                    tab.setIcon(R.drawable.ic_main)
                }
                1 -> {
                    tab.text = getString(R.string.favorite)
                    tab.setIcon(R.drawable.ic_favorite)
                }
            }
        }.attach()
    }
}