package com.muhammetkdr.weatherapp.ui.search

import androidx.fragment.app.viewModels
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding,SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel by viewModels<SearchViewModel>()

}