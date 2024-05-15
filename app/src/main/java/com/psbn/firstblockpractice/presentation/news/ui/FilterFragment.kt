package com.psbn.firstblockpractice.presentation.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.psbn.firstblockpractice.R
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.databinding.FragmentFilterBinding
import com.psbn.firstblockpractice.presentation.models.Filter
import com.psbn.firstblockpractice.presentation.utills.prefmanager.filter.FilterPreferenceManager

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding
        get() = _binding ?: throw BindingException(
            "FragmentFilterBinding is null"
        )
    private var settings: MutableList<Filter> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings = FilterPreferenceManager.filterList.toMutableList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStartedSettingsValue()
        setSettingsListeners()
        setBackButton()
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            val direction =
                FilterFragmentDirections.actionFilterFragmentToNewsFragment()
            findNavController().navigate(direction)
        }
    }

    private fun setStartedSettingsValue() {
        with(binding) {
            kidsSwitch.isChecked = FilterPreferenceManager.getFilterPref(Filter.KIDS_ID).isActive
            adultSwitch.isChecked = FilterPreferenceManager.getFilterPref(Filter.ADULTS_ID).isActive
            elderlySwitch.isChecked =
                FilterPreferenceManager.getFilterPref(Filter.ELDERLY_ID).isActive
            animalSwitch.isChecked =
                FilterPreferenceManager.getFilterPref(Filter.ANIMALS_ID).isActive
            eventsSwitch.isChecked =
                FilterPreferenceManager.getFilterPref(Filter.EVENTS_ID).isActive
        }
    }

    private fun setSettingsListeners() {
        binding.kidsSwitch.setOnCheckedChangeListener { _, isChecked ->
            FilterPreferenceManager.getFilterPref(Filter.KIDS_ID).isActive = isChecked
        }
        binding.adultSwitch.setOnCheckedChangeListener { _, isChecked ->
            FilterPreferenceManager.getFilterPref(Filter.ADULTS_ID).isActive = isChecked
        }
        binding.elderlySwitch.setOnCheckedChangeListener { _, isChecked ->
            FilterPreferenceManager.getFilterPref(Filter.ELDERLY_ID).isActive = isChecked
        }
        binding.animalSwitch.setOnCheckedChangeListener { _, isChecked ->
            FilterPreferenceManager.getFilterPref(Filter.ANIMALS_ID).isActive = isChecked
        }
        binding.eventsSwitch.setOnCheckedChangeListener { _, isChecked ->
            FilterPreferenceManager.getFilterPref(Filter.EVENTS_ID).isActive = isChecked
        }
        binding.imageButtonApplyFilter.setOnClickListener {
            saveSettings()
        }
    }

    private fun saveSettings() {
        FilterPreferenceManager.saveFilterSettings()
        Toast.makeText(requireContext(), R.string.toast_prefs_success, Toast.LENGTH_SHORT).show()
    }
}
