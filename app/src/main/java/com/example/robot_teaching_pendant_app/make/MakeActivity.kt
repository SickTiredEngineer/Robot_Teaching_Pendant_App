package com.example.robot_teaching_pendant_app.make

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.MakeActivityBinding
import com.example.robot_teaching_pendant_app.databinding.MakeExtActivityBinding
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity

class MakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Make(작업 환경) 화면을 바인딩 합니다.
        val binding = MakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //좌측 상단에 위치한 화면 이동 메뉴 버튼
        val makeMenuBt = binding.makeMenuBt

        val makePowerBt = binding.makePowerBt
        val treeExtBt = binding.treeExtBt

        /* TREE를 제외한 우측에 요소들을 표시해주는 View 와 기본적으로 보여지는 화면(Default) 설정이며, 확장 버튼 클릭 시 ext Activity로 교체합니다. */
        val sideViewer = binding.sideView


        //Ext, Default Fragment 를 변수에 초기화합니다.
        val fragmentExt = MakeExt()
        val fragmentDefault = MakeDefault()

        //Ext,  Default 모드에 맞게 초기 상태를 설정합니다.
        val initialFragment = if (treeExtBt.isChecked) fragmentExt else fragmentDefault
        supportFragmentManager.beginTransaction()
            .add(sideViewer.id, initialFragment)
            .commit()

        if (savedInstanceState == null) { // 처음 액티비티가 생성되었을 때만 프래그먼트 추가
            val fragment = MakeDefault() //
            supportFragmentManager.beginTransaction()
                .replace(sideViewer.id, fragment)
                .commit()
        }

        //Ext tree 토글 버튼 클릭 시 동작입니다.
        treeExtBt.setOnCheckedChangeListener { _, isChecked ->
            val fragment = if(isChecked) {
                MakeExt()
                } else {
                    MakeDefault()
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(sideViewer.id, fragment)
                        .commit()
                }


        //좌측 상단에 위치한 Menu 버튼 동작, Dialog 형식으로 해당 화면으로 이동할 수 있는 기능
        makeMenuBt.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("메인 화면", "실행", "환경 설정")

            //바깥 화면을 클릭 하여 창을 닫을 수 있습니다.
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