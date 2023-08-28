package com.example.robot_teaching_pendant_app.setup
//import com.example.robot_teaching_pendant_app.setup.SetupInflate

import android.content.Context
//import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.SetupActivityBinding





class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = SetupActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val setupViewer = binding.setupViewer



        val setupCobotBt = binding.setupCobotBt
        val setupToolBt = binding.setupToolBt
        val setupSystemBt = binding.setupSystemBt

        setupCobotBt.setOnClickListener(View.OnClickListener {
//            SetupInflate().cobotSelect()
//
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.setup_cobot_activity, setupViewer, true)

        })

        setupToolBt.setOnClickListener(View.OnClickListener {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.setup_cobot_activity, setupViewer, true)
        })

        setupSystemBt.setOnClickListener(View.OnClickListener {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.setup_cobot_activity, setupViewer, true)
        })






    }
}
