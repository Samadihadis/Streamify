package com.samadihadis.streamify.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.samadihadis.streamify.R
import com.samadihadis.streamify.databinding.ActivityMainBinding
import com.samadihadis.streamify.utils.SharePreferencesManager
import com.samadihadis.streamify.utils.SharePreferencesManager.Companion.IS_DARK_MODE_ENABLE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storage by lazy {
        SharePreferencesManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupTheme()
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.navMainFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.videoListFragment, R.id.settingFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupTheme() {
        val isDarkModeEnabled = storage.retrieveBoolean(IS_DARK_MODE_ENABLE)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkModeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
    }

}
