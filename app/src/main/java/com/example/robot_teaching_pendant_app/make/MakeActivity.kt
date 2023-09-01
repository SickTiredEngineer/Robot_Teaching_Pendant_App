package com.example.robot_teaching_pendant_app.make

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.MakeActivityBinding
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity

class MakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //좌측 상단의 메뉴 버튼
        val makeMenu = binding.makeMenuBt
        makeMenu.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("메인 화면", "실행", "환경 설정")
            menuDialog.setCancelable(true)
            menuDialog.setItems(menuArray) { _, p1 ->
                when (menuArray[p1]) {
                    "메인 화면" -> {
                        val nextIntent = Intent(this@MakeActivity, MainActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }

                    "실행" -> {
                        val nextIntent = Intent(this@MakeActivity, PlayActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }

                    "환경 설정" -> {
                        val nextIntent = Intent(this@MakeActivity, SetupActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                }
            }
            menuDialog.show()
        }
    }
}