package com.example.robot_teaching_pendant_app.play

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.PlayActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity


class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //좌측 상단의 메뉴 버튼
        val playMenu = binding.playMenuBt
        playMenu.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("작업 환경", "메인 화면", "환경 설정")
            menuDialog.setCancelable(true)
            menuDialog.setItems(menuArray, object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when("${menuArray[p1]}"){
                        "작업 환경" ->{
                            val nextIntent = Intent(this@PlayActivity, MakeActivity::class.java)
                            startActivity(nextIntent)
                            finish()
                        }

                        "메인 화면" ->{
                            val nextIntent = Intent(this@PlayActivity, MainActivity::class.java)
                            startActivity(nextIntent)
                            finish()
                        }

                        "환경 설정" ->{
                            val nextIntent = Intent(this@PlayActivity, SetupActivity::class.java)
                            startActivity(nextIntent)
                            finish()
                        }
                    }
                }
            })
            menuDialog.show()
        }
    }
}