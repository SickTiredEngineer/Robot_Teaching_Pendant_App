package com.example.robot_teaching_pendant_app.make

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.MakeActivityBinding
import com.example.robot_teaching_pendant_app.databinding.MakeDefaultActivityBinding
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity

class MakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val makePowerBt = binding.makePowerBt
        val defBinding = MakeDefaultActivityBinding.inflate(layoutInflater)


        /* TREE를 제외한 우측에 요소들을 표시해주는 View 와 기본적으로 보여지는 화면(Default) 설정으로 확장 버튼 클릭 시, ext Activity로 교체합니다. */
        val sideViewer = binding.sideView
        sideViewer.removeAllViews()
        sideViewer.addView(defBinding.root)




        //좌측 상단에 위치한 Menu 버튼 동작, Dialog 형식으로 해당 화면으로 이동할 수 있는 기능
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



        //우측 하단에 위치한 파워 버튼을 클릭 시 동작입니다.
        makePowerBt.setOnClickListener{
            Toast.makeText(this@MakeActivity,"전원 버튼 클릭", Toast.LENGTH_SHORT).show()
        }
    }
}