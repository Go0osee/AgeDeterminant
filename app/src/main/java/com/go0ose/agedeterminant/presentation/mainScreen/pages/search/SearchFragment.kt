package com.go0ose.agedeterminant.presentation.mainScreen.pages.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.go0ose.agedeterminant.AgeApplication
import com.go0ose.agedeterminant.R
import com.go0ose.agedeterminant.databinding.FragmentSearchBinding
import com.go0ose.agedeterminant.presentation.mainScreen.SharedViewModel
import com.go0ose.agedeterminant.presentation.model.Age
import com.go0ose.agedeterminant.presentation.model.MainState
import com.go0ose.agedeterminant.utils.appComponent
import com.go0ose.agedeterminant.utils.hideKeyboard
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()

    @Inject
    lateinit var sharedViewModel: SharedViewModel

    private val searchViewModel: SearchViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AgeApplication.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListener()
        initObservers()
    }

    private fun clickListener() {

        with(binding) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchViewModel.searchAge(searchView.query.toString())
                    searchView.hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            searchView.setOnCloseListener {
                searchViewModel.setStateDefault()
                false
            }

            shareBtn.setOnClickListener {
                shareResult(searchView.query.toString(), ageResult.number.toString())
            }

            addFavoriteBtn.setOnClickListener {
                searchViewModel.addFavoriteAge(
                    Age(
                        name = searchView.query.toString(),
                        age = ageResult.number
                    )
                )
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            searchViewModel.mainState.collectLatest { state ->
                when (state) {
                    is MainState.DefaultState -> {
                        replaceView(
                            binding.successResultConstraint,
                            binding.loading,
                            binding.enterNameConstraint
                        )
                    }
                    is MainState.LoadingState -> {
                        replaceView(
                            binding.enterNameConstraint,
                            binding.successResultConstraint,
                            binding.loading
                        )
                    }
                    is MainState.SuccessState -> {
                        binding.ageResult.number = state.age.age
                        replaceView(
                            binding.enterNameConstraint,
                            binding.loading,
                            binding.successResultConstraint
                        )
                    }
                    is MainState.ErrorState -> {
                        replaceView(
                            binding.successResultConstraint,
                            binding.loading,
                            binding.enterNameConstraint
                        )
                        Toast.makeText(
                            requireContext(),
                            resources.getText(state.message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is MainState.AddFavoriteState -> {
                        Toast.makeText(
                            requireContext(),
                            resources.getText(state.message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun replaceView(oldView1: View, oldView2: View, newView: View) {
        oldView1.animate()
            .alpha(0f)
            .setDuration(1000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    oldView1.visibility = View.GONE
                    oldView2.visibility = View.GONE
                    newView.visibility = View.VISIBLE
                    newView.alpha = 0f
                    newView.animate()
                        .alpha(1f)
                        .setDuration(500)
                        .setListener(null)
                }
            })
    }

    private fun shareResult(name: String, age: String) {

        val text =
            getString(R.string.share) + " " + name + " " + age + " " + getString(R.string.years_old)

        val intent = Intent().apply {
            type = "text/plain"
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
        }

        val intentChooser = Intent.createChooser(intent, "")
        startActivity(intentChooser)
    }
}