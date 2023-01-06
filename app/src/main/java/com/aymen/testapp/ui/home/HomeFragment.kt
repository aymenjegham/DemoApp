package com.aymen.testapp.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aymen.framework.global.helper.Navigation
import com.aymen.testapp.databinding.FragmentHomeBinding
import com.aymen.testapp.ui.base.BaseFragment
import com.aymen.testapp.ui.home.adapter.UserAdapter
import com.aymen.testapp.ui.utils.decideOnState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var userAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding =
            FragmentHomeBinding.inflate(layoutInflater, container, false)

        bind(binding)

        registerHomeObservers(binding)

        return binding.root
    }


    private fun bind(binding: FragmentHomeBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        userAdapter = UserAdapter(
            viewModel.navigate(),
            requireContext(),
            picasso
        )
        binding.adapter = userAdapter
    }



    private fun registerHomeObservers(binding: FragmentHomeBinding) {
        registerBaseObservers(viewModel)
        registerSubmitAdapter()
        registerAdapterLoadingObserver(binding)
    }

    private fun registerAdapterLoadingObserver(binding: FragmentHomeBinding) {
        userAdapter.addLoadStateListener {

            it.decideOnState(
                showLoading = { visible ->
                        binding.refresh.isRefreshing = false
                },
                showEmptyState = { visible ->

                },
                showError = { message ->
                   showError(message)

                },
                userAdapter.itemCount

            )
        }
    }

    private fun registerSubmitAdapter() {
        viewModel.fetchUsers.observe(viewLifecycleOwner) { pagingDataUser ->
            viewLifecycleOwner.lifecycleScope.launch {
                userAdapter.submitData(lifecycle, pagingDataUser)
            }
        }
    }

    private fun showError(msg: String) {
            viewModel.showSnackBar(msg) {}
    }

    override fun navigate(navigationTo: Navigation) {
        when (navigationTo) {

            is Navigation.NavigationDetails ->
                findNavController().navigate(
                    HomeFragmentDirections.toDetails(navigationTo.userItem)
                )

            else -> {}
        }
    }

}