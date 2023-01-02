package com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.go0ose.agedeterminant.AgeApplication
import com.go0ose.agedeterminant.R
import com.go0ose.agedeterminant.databinding.FragmentFavoriteBinding
import com.go0ose.agedeterminant.presentation.mainScreen.SharedViewModel
import com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.recycler.Listener
import com.go0ose.agedeterminant.presentation.mainScreen.pages.favorite.recycler.FavoriteAgeAdapter
import com.go0ose.agedeterminant.presentation.model.FavoriteState
import com.go0ose.agedeterminant.presentation.model.ItemAge
import com.go0ose.agedeterminant.utils.appComponent
import com.go0ose.agedeterminant.utils.showDialog
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()

    @Inject
    lateinit var sharedViewModel: SharedViewModel

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        requireActivity().appComponent.viewModelsFactory()
    }

    private val onItemListener by lazy {
        object : Listener {
            override fun onLongItemClick(items: List<ItemAge>, itemAge: ItemAge) {
                favoriteViewModel.updateDeleteList(items, itemAge)
                showDeleteBtn(true)
            }

            override fun itemSelectedIsEmpty(items: List<ItemAge>) {
                favoriteViewModel.updateList(items)
                showDeleteBtn(false)
            }
        }
    }

    private var adapter = FavoriteAgeAdapter(onItemListener)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AgeApplication.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFavorite.adapter = adapter
        initObservers()
        clickListener()
    }

    override fun onResume() {
        super.onResume()
        showDeleteBtn(false)
        favoriteViewModel.runFlow()
    }

    private fun initObservers() {

        lifecycleScope.launchWhenCreated {
            favoriteViewModel.state.collectLatest { state ->

                when (state) {
                    is FavoriteState.DefaultState -> {
                        adapter.submitList(state.list, state.show)
                    }
                    is FavoriteState.DeleteState -> {
                        adapter.submitList(state.list, state.show)
                    }
                }
            }
        }
    }

    private fun clickListener() {
        binding.deleteBtn.setOnClickListener {
            val itemSelected = adapter.itemSelectedList
            val items = adapter.items

            adapter = FavoriteAgeAdapter(onItemListener)
            binding.recyclerFavorite.adapter = adapter
            showDialog(
                onPositiveButtonClick = {
                    favoriteViewModel.deleteItem(itemSelected, items)
                    Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_SHORT).show()
                    showDeleteBtn(false)
                },
                onNegativeButtonClick = {
                    favoriteViewModel.setDefaultState()
                    showDeleteBtn(false)
                }
            )
        }
    }

    private fun showDeleteBtn(show: Boolean) {
        if (show) {
            binding.deleteBtn.visibility = View.VISIBLE
        } else {
            binding.deleteBtn.visibility = View.GONE
        }
    }
}