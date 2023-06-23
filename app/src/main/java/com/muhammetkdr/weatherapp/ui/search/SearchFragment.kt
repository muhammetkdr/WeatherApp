package com.muhammetkdr.weatherapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.collectFlow
import com.muhammetkdr.weatherapp.common.extensions.observeIfNotNull
import com.muhammetkdr.weatherapp.common.extensions.showSnackbar
import com.muhammetkdr.weatherapp.databinding.FragmentSearchBinding
import com.muhammetkdr.weatherapp.ui.search.rv.CitiesRvAdapter
import com.muhammetkdr.weatherapp.ui.uistate.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel by viewModels<SearchViewModel>()

    private val adapter: CitiesRvAdapter by lazy { CitiesRvAdapter(::itemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchResponse()
        setupRv()
        observeQueryList()
        initDataBinding()
    }

    private fun initDataBinding() {
        binding.searchViewmodel = viewModel
    }

    private fun observeQueryList() {
        viewModel.citiesQueryList.observeIfNotNull(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupRv() {
        binding.rvSearch.adapter = adapter
    }

    private fun observeSearchResponse() {
        collectFlow(viewModel.cityList) {
                when (it) {
                    is UiState.Success -> {
                            setSearchUiState(true)
                            adapter.submitList(it.data)
                            viewModel.setCityListData(it.data)
                    }
                    is UiState.Error -> {
                        setSearchUiState(false)
                        requireView().showSnackbar(it.error)
                    }
                    is UiState.Loading -> {
                        setSearchUiState(false)
                    }
                }
        }
    }

    private fun setSearchUiState(isVisible: Boolean){
        with(binding){
            searchTextField.isVisible = isVisible
            rvSearch.isVisible = isVisible
            searchProgressbar.isVisible = !isVisible
        }
    }

    private fun itemClick(data: SearchUiData) {
        val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment(data)
        findNavController().navigate(action)
    }
}