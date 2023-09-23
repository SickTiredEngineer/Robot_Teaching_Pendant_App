package com.example.robot_teaching_pendant_app.play

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.connect.FragmentDialogConnector
import com.example.robot_teaching_pendant_app.databinding.PlayActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity
import com.example.robot_teaching_pendant_app.system.FragmentPowerOff


class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playPowerBt = binding.playPowerBt

        //좌측 상단에 위치한 화면 이동 메뉴 버튼
        val playMenuBt = binding.playMenuBt

        val connectBt = binding.playConnectBt

        connectBt.setOnClickListener{
            val dialogFragment = FragmentDialogConnector()
            dialogFragment.show(supportFragmentManager,null)
        }



        //좌측 상단에 위치한 Menu 버튼 동작, Dialog 형식으로 해당 화면으로 이동할 수 있는 기능
        playMenuBt.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("작업 환경", "메인 화면", "환경 설정")

            //바깥 화면을 클릭 하여 창을 닫을 수 있습니다.
            menuDialog.setCancelable(true)
            menuDialog.setItems(menuArray) { _, p1 ->
                when (menuArray[p1]) {
                    "작업 환경" -> {
                        val nextIntent = Intent(this@PlayActivity, MakeActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                    "메인 화면" -> {
                        val nextIntent = Intent(this@PlayActivity, MainActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                    "환경 설정" -> {
                        val nextIntent = Intent(this@PlayActivity, SetupActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                }
            }
            menuDialog.show()
        }


        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        playPowerBt.setOnClickListener{
            val dialogFragment = FragmentPowerOff()
            dialogFragment.show(supportFragmentManager,null)
        }


    }
}