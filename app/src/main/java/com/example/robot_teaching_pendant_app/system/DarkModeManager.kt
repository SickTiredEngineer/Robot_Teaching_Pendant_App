package com.example.robot_teaching_pendant_app.system

import android.content.Context
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

class DarkModeManager(context: Context, private val switch: Switch) {

    /**
    LightMode 와 DarkMode 전환을 관리하는 클래스 파일입니다.
     */


    private val sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE)

    init {
        val isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        switch.isChecked = isNightModeOn

        switch.setOnCheckedChangeListener { _, isChecked ->
            toggleDarkMode(isChecked)
        }
    }

    private fun toggleDarkMode(isOn: Boolean) {
        if (isOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            saveModePreference(true)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            saveModePreference(false)
        }
    }

    private fun saveModePreference(isNightMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("night", isNightMode)
        editor.apply()
    }
}