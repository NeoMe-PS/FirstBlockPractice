package com.psbn.firstblockpractice.help.presentation.ui

import android.content.Context
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
import com.ps_pn.firstblockpractice.help.databinding.FragmentHelpBinding
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.help.di.DaggerHelpComponent
import com.psbn.firstblockpractice.help.di.HelpDepsProvider
import com.psbn.firstblockpractice.help.presentation.adapter.CategoryAdapter
import com.psbn.firstblockpractice.help.presentation.viewModel.HelpUIState
import com.psbn.firstblockpractice.help.presentation.viewModel.HelpViewModel
import com.psbn.firstblockpractice.help.presentation.viewModel.HelpViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding: FragmentHelpBinding
        get() = _binding ?: throw BindingException("FragmentHelpBinding is null")

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()

    @Inject
    lateinit var viewModelFactory: HelpViewModelFactory
    lateinit var viewModel: HelpViewModel
    private val mainDispatcher = Dispatchers.Main

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val componentDeps = (context.applicationContext as HelpDepsProvider).getHelpDeps()
        val component =
            DaggerHelpComponent.factory().create(componentDeps)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
