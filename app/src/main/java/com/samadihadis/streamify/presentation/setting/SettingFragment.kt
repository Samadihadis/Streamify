package com.samadihadis.streamify.presentation.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.samadihadis.streamify.databinding.FragmentSettingBinding
import com.samadihadis.streamify.utils.SharePreferencesManager
import com.samadihadis.streamify.utils.SharePreferencesManager.Companion.IS_DARK_MODE_ENABLE

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private lateinit var viewModel: SettingViewModel
    private val storage by lazy {
        SharePreferencesManager(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SettingViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeThemeSettings()
    }

    private fun changeThemeSettings() = binding.darkThemeSettings.themeSwitch.apply {
        isChecked = storage.retrieveBoolean(IS_DARK_MODE_ENABLE)
        setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                storage.saveBoolean(IS_DARK_MODE_ENABLE , true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                storage.saveBoolean(IS_DARK_MODE_ENABLE , false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}