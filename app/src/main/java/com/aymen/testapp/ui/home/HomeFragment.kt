package com.aymen.testapp.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aymen.core.domain.Result
import com.aymen.framework.global.helper.Navigation
import com.aymen.testapp.databinding.FragmentHomeBinding
import com.aymen.testapp.ui.base.BaseFragment
import com.aymen.testapp.ui.home.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var userAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        setupRefresh(binding)

    }

    private fun registerSubmitAdapter() {
        viewModel.resultUserList.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { list ->
                        userAdapter.submitList(list)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRefresh(binding: FragmentHomeBinding) {
        binding.refresh.setOnRefreshListener {
            viewModel.fetchUsers()
            binding.refresh.isRefreshing = false
        }
    }

    private fun showError(msg: String) {
        viewModel.showSnackBar(msg) { viewModel.fetchUsers() }
    }

    override fun navigate(navigationTo: Navigation) {
        when (navigationTo) {

            is Navigation.NavigationDetails ->
                findNavController().navigate(
                HomeFragmentDirections.toDetails(navigationTo.userItem)
            )

            else -> null
        }
    }


}