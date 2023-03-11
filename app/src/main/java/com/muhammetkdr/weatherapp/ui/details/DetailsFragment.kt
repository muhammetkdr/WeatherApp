package com.muhammetkdr.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.data.*
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.*
import com.muhammetkdr.weatherapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(FragmentDetailsBinding::inflate) {

    override val viewModel by viewModels<DetailsViewModel>()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLineChart()
        initToolbar()
        initDataBinding()
        viewModel.getData(args.datesAndTimes)
    }

    private fun initDataBinding() = with(binding) {
        detailViewModel = viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    private fun initToolbar() = with(binding) {
        detailToolbarView.onBackPressed {
            findNavController().popBackStack()
        }
        detailToolbarView.updateTitle(args.datesAndTimes.date)
    }

    private fun initLineChart() = viewModel.barEntry.observe(viewLifecycleOwner) {
        entyListObserver(it)
    }

    private fun entyListObserver(data: List<Entry>?) = with(binding) {
        if (data == null) {
            detailScreenProgressbar.visible()
            detailViewLinearLayout.invisible()
        } else {
            detailScreenProgressbar.gone()
            detailViewLinearLayout.visible()
            val lineDataSet = LineDataSet(data, String.EMPTY)
            lineChartDetailsPage.setLineChart(lineDataSet, viewModel.hoursList)
        }
    }

}