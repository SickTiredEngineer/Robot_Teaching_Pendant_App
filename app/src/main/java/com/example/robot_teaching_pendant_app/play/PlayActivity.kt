package com.example.robot_teaching_pendant_app.play

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.command_tree.CommandTreeFragment
import com.example.robot_teaching_pendant_app.connect.ConnectorDialogFragment
import com.example.robot_teaching_pendant_app.databinding.PlayActivityBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import com.example.robot_teaching_pendant_app.make.MakeDefaultFragment
import com.example.robot_teaching_pendant_app.setup.SetupActivity
import com.example.robot_teaching_pendant_app.system.PowerOffDialogFragment


class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playPowerBt = binding.playPowerBt

        //좌측 상단에 위치한 화면 이동 메뉴 버튼
        val playMenuBt = binding.playMenuBt

        val connectBt = binding.playConnectBt
        val playTreeView = binding.playTreeView


        if (savedInstanceState == null) {
            //트리 프래그먼트를 삽입하는 코드입니다.
            val treeFragment = CommandTreeFragment()
            supportFragmentManager.beginTransaction()
                .replace(playTreeView.id, treeFragment)
                .commit()
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

        //우측 하단에 위치한 연결 버튼을 누를 시, ConnectorDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 Connect 디렉토리의 ConnectorDialogFragment를 참고하십시요.
        connectBt.setOnClickListener{
            val dialogFragment = ConnectorDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }

        //우측 하단에 위치한 전원 버튼을 누를 시, PowerOffDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 System 디렉토리의 PowerOffDialogFragment를 참고하십시요.
        playPowerBt.setOnClickListener{
            val dialogFragment = PowerOffDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }


    }
}