//package com.example.robot_teaching_pendant_app.system
//
//import android.content.Context
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.robot_teaching_pendant_app.R
//import com.example.robot_teaching_pendant_app.databinding.ConnectActivityBinding
//
//class ConnectManager: AppCompatActivity() {
//
//
//
//    //Connect Activity 바인딩
//    private val conBinding = ConnectActivityBinding.inflate(layoutInflater)
//    private val connectBt = conBinding.connectBt
//    private val disconnectBt = conBinding.disconnectBt
//
//    companion object {
//        lateinit var mContext: Context
//    }
//
//
//    public fun connectControlBox()
//    {
//        connectBt.isEnabled = false
//
//        disconnectBt.isEnabled = true
//        disconnectBt.setBackgroundResource(R.drawable.color_red_box)
//
//    }
//}
