package com.example.robot_teaching_pendant_app.setup

//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
//import android.view.WindowManager
//import android.widget.CompoundButton
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.constraintlayout.widget.ConstraintLayout
import com.example.robot_teaching_pendant_app.databinding.SetupActivityBinding

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = SetupActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
