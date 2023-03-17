package com.muhammetkdr.weatherapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.observeIfNotNull
import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.databinding.FragmentSearchBinding
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import com.muhammetkdr.weatherapp.ui.search.rv.CitiesRvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel by viewModels<SearchViewModel>()

    private val adapter: CitiesRvAdapter by lazy { CitiesRvAdapter(::itemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchResponse()

        setEditTextChangedListener()

        setupRv()

        observeQueryList()


    }

    private fun observeQueryList() {
        viewModel.citiesQueryList.observeIfNotNull(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun setupRv() {
        binding.rvSearch.adapter = adapter
    }

    private fun setEditTextChangedListener() {
        binding.searchTextField.editText?.addTextChangedListener {editable ->
                editable?.let {
                    viewModel.filterCityQuery(it.toString())
            }
        }
    }

    private fun observeSearchResponse() = lifecycleScope.launchWhenStarted {
        viewModel.cityList.collect { Resource ->
            when (Resource) {
                is Resource.Success -> {
                    Resource.data.apply {
                        adapter.submitList(this)
                        viewModel.setCityListData(this)
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }
    }

    private fun itemClick(data: CitiesEntity) {
        val action = SearchFragmentDirections.actionSearchFragmentToHomeFragment(data)
        findNavController().navigate(action)
    }

}