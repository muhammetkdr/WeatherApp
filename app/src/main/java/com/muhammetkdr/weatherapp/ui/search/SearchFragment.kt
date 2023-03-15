package com.muhammetkdr.weatherapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.showSnackbar
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchResponse()

        setRootViewClickListener()

        setEditTextChangedListener()

    }


    private fun setEditTextChangedListener() {
        binding.searchTextField.editText?.addTextChangedListener {
            lifecycleScope.launchWhenStarted {
                it?.let {
                    if (it.toString().isNotEmpty() && it.count() > 2) {
                        viewModel.getData(it.toString())
                    }
                }
            }
        }
    }

    private fun observeSearchResponse() = lifecycleScope.launchWhenStarted {
        viewModel.currentWeather.collectLatest { Resource ->
            when(Resource) {
                is Resource.Success -> {
                    Resource.data.let {
                        requireView().showSnackbar(it.cityName)
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }

    }

    private fun setRootViewClickListener() = with(binding) {
        root.setOnClickListener {
            hideKeyBoard()
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        binding.searchTextField.clearFocus()
    }
}