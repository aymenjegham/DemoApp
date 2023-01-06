package com.aymen.testapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aymen.testapp.databinding.FragmentDetailsBinding
import com.aymen.testapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : BaseFragment() {


    private val viewModel by viewModels<DetailsViewModel>()

    private val args by navArgs<DetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding =
            FragmentDetailsBinding.inflate(layoutInflater, container, false)

        bind(binding)

        return binding.root
    }

    private fun bind(binding: FragmentDetailsBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.setUserItem(args.userItem)

        registerDetailsObservers()

    }

    private fun registerDetailsObservers() {
        registerBaseObservers(viewModel)
    }

}