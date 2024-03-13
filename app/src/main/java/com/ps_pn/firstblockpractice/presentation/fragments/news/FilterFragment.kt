package com.ps_pn.firstblockpractice.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.databinding.FragmentFilterBinding
import com.ps_pn.firstblockpractice.presentation.utills.PreferenceManager
import com.ps_pn.firstblockpractice.presentation.models.Filter
import com.ps_pn.firstblockpractice.presentation.utills.navigator


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentFilterBinding is null")
    private var settings: MutableList<Filter> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings = PreferenceManager.getFilterPreference().toMutableList()
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
            this.navigator().back()
        }
    }

    private fun setStartedSettingsValue() {
        with(binding) {
            kidsSwitch.isChecked = settings[0].isActive
            adultSwitch.isChecked = settings[1].isActive
            elderlySwitch.isChecked = settings[2].isActive
            animalSwitch.isChecked = settings[3].isActive
            eventsSwitch.isChecked = settings[4].isActive
        }
    }

    private fun setSettingsListeners() {
        binding.kidsSwitch.setOnCheckedChangeListener { _, isChecked ->
            settings[0].isActive = isChecked
        }
        binding.adultSwitch.setOnCheckedChangeListener { _, isChecked ->
            settings[1].isActive = isChecked
        }
        binding.elderlySwitch.setOnCheckedChangeListener { _, isChecked ->
            settings[2].isActive = isChecked
        }
        binding.animalSwitch.setOnCheckedChangeListener { _, isChecked ->
            settings[3].isActive = isChecked
        }
        binding.eventsSwitch.setOnCheckedChangeListener { _, isChecked ->
            settings[4].isActive = isChecked
        }
        binding.imageButtonApplyFilter.setOnClickListener {
            saveSettings()
        }
    }


    private fun saveSettings() {
        PreferenceManager.saveFilterSettings(settings)
        Toast.makeText(requireContext(), "Настройки сохранены", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}
