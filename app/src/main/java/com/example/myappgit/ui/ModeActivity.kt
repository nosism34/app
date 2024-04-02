package com.example.myappgit.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.myappgit.Model.ViewModelFactory
import com.example.myappgit.Model.temaViewModel
import com.example.myappgit.R
import com.example.myappgit.util.SettingPreference
import com.example.myappgit.util.dataStore
import com.google.android.material.switchmaterial.SwitchMaterial
class ModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode)
        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        val pref = SettingPreference.getInstance(application.dataStore)
        val TemaViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            temaViewModel::class.java
        )
        TemaViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            TemaViewModel.saveThemeSetting(isChecked)
        }
    }
}