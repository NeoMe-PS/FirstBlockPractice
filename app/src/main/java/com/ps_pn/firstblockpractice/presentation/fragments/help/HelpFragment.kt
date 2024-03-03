package com.ps_pn.firstblockpractice.presentation.fragments.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.data.StubData
import com.ps_pn.firstblockpractice.databinding.FragmentHelpBinding
import com.ps_pn.firstblockpractice.presentation.adapter.help.CategoryAdapter

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding: FragmentHelpBinding
        get() = _binding ?: throw RuntimeException("FragmentHelpBinding is null")

    private val categoryAdapter: CategoryAdapter = CategoryAdapter()

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
        binding.categoryRv.adapter = categoryAdapter
        fillAdapter()
    }

    private fun fillAdapter() {
        categoryAdapter.submitList(StubData.fillCategoriesStubData())
    }

    companion object {
        fun newInstance() = HelpFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
