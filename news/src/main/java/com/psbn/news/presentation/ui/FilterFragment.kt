package com.psbn.news.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.psbn.firstblockpractice.core.R
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.news.databinding.FragmentFilterBinding
import com.psbn.news.di.DaggerNewsComponent
import com.psbn.news.di.NewsDepsProvider
import com.psbn.news.presentation.filter.FilterPreferenceManager
import com.psbn.news.presentation.models.Filter
import javax.inject.Inject

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding
        get() = _binding ?: throw BindingException("FragmentFilterBinding is null")

    @Inject
    lateinit var filterPreferenceManager: FilterPreferenceManager
    private var settings: MutableList<Filter> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings = filterPreferenceManager.filterList.toMutableList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val componentDeps = (context.applicationContext as NewsDepsProvider).getNewsDeps()
        val component =
            DaggerNewsComponent.factory().create(requireActivity().application, componentDeps)
        component.inject(this)
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
            kidsSwitch.isChecked = filterPreferenceManager.getFilterPref(Filter.KIDS_ID).isActive
            adultSwitch.isChecked = filterPreferenceManager.getFilterPref(Filter.ADULTS_ID).isActive
            elderlySwitch.isChecked =
                filterPreferenceManager.getFilterPref(Filter.ELDERLY_ID).isActive
            animalSwitch.isChecked =
                filterPreferenceManager.getFilterPref(Filter.ANIMALS_ID).isActive
            eventsSwitch.isChecked =
                filterPreferenceManager.getFilterPref(Filter.EVENTS_ID).isActive
        }
    }

    private fun setSettingsListeners() {
        binding.kidsSwitch.setOnCheckedChangeListener { _, isChecked ->
            filterPreferenceManager.getFilterPref(Filter.KIDS_ID).isActive = isChecked
        }
        binding.adultSwitch.setOnCheckedChangeListener { _, isChecked ->
            filterPreferenceManager.getFilterPref(Filter.ADULTS_ID).isActive = isChecked
        }
        binding.elderlySwitch.setOnCheckedChangeListener { _, isChecked ->
            filterPreferenceManager.getFilterPref(Filter.ELDERLY_ID).isActive = isChecked
        }
        binding.animalSwitch.setOnCheckedChangeListener { _, isChecked ->
            filterPreferenceManager.getFilterPref(Filter.ANIMALS_ID).isActive = isChecked
        }
        binding.eventsSwitch.setOnCheckedChangeListener { _, isChecked ->
            filterPreferenceManager.getFilterPref(Filter.EVENTS_ID).isActive = isChecked
        }
        binding.imageButtonApplyFilter.setOnClickListener {
            saveSettings()
        }
    }

    private fun saveSettings() {
        filterPreferenceManager.saveFilterSettings()
        Toast.makeText(requireContext(), R.string.toast_prefs_success, Toast.LENGTH_SHORT).show()
    }
}
