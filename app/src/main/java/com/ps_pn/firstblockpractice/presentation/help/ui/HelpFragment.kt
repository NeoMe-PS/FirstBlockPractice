package com.ps_pn.firstblockpractice.presentation.help.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ps_pn.firstblockpractice.core.App
import com.ps_pn.firstblockpractice.core.exception.BindingException
import com.ps_pn.firstblockpractice.databinding.FragmentHelpBinding
import com.ps_pn.firstblockpractice.di.AppComponent
import com.ps_pn.firstblockpractice.presentation.ViewModelFactory
import com.ps_pn.firstblockpractice.presentation.help.adapter.CategoryAdapter
import com.ps_pn.firstblockpractice.presentation.help.viewModel.HelpUIState
import com.ps_pn.firstblockpractice.presentation.help.viewModel.HelpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding: FragmentHelpBinding
        get() = _binding ?: throw BindingException("FragmentHelpBinding is null")

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()
    lateinit var viewModel: HelpViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component: AppComponent by lazy {
        (requireActivity().application as App).component
    }
    private val mainDispatcher = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HelpViewModel::class.java]
        binding.categoryRv.adapter = categoryAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is HelpUIState.Loading -> {
                            showProgressBar()
                        }

                        is HelpUIState.Response -> {
                            categoryAdapter.submitList(state.categories)
                            hideProgressBar()
                        }

                        is HelpUIState.Error -> {
                            withContext(mainDispatcher) {
                                showErrorMsg()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showErrorMsg() {
        Toast.makeText(this@HelpFragment.requireContext(), "Some error", Toast.LENGTH_SHORT)
            .show()
    }

    private fun showProgressBar() {
        binding.categoryProgressBar.isVisible = true
        binding.categoryRv.isVisible = false
    }

    private fun hideProgressBar() {
        binding.categoryProgressBar.isVisible = false
        binding.categoryRv.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
