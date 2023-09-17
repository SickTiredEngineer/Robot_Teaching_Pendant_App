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
import com.example.robot_teaching_pendant_app.databinding.JGlobalActivityBinding
import com.example.robot_teaching_pendant_app.databinding.JJointActivityBinding
import com.example.robot_teaching_pendant_app.databinding.JLocalActivityBinding
import com.example.robot_teaching_pendant_app.databinding.JUserActivityBinding
import com.example.robot_teaching_pendant_app.databinding.MakeActivityBinding
import com.example.robot_teaching_pendant_app.databinding.MakeDefaultActivityBinding
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

        //우측에 나타나는 전체적인 화면 Binding 입니다.
        val defBinding = MakeDefaultActivityBinding.inflate(layoutInflater)


        //Jog Mode -> Smooth , Tick 을 선택할 때 사용하는 버튼입니다.
        val makeSmoothBt = defBinding.makeSmoothBt
        val makeTickBt = defBinding.makeTickBt

        //Tick 모드 활성화 시 나오는 EditText 입니다.
        val tickJoint = defBinding.tickJoint
        val tickOri = defBinding.tickOri
        val tickDist = defBinding.tickDist

        //Tick 모드 활성화 시 나오는 EditText 설명 TextView 입니다.
        val jogModeTv1 = defBinding.jogModeTv1
        val jogModeTv2 = defBinding.jogModeTv2
        val jogModeTv3 = defBinding.jogModeTv3

        //기본적으로 Tick 모드에서 사용하는 요소들은 가려지게 설정합니다.
        tickDist.isInvisible = true
        tickJoint.isInvisible = true
        tickOri.isInvisible = true
        jogModeTv1.isInvisible = true
        jogModeTv2.isInvisible = true
        jogModeTv3.isInvisible = true


        //조그(Global, Local, User, Joint) 를 선택하는 버튼들을 변수에 초기화 시킵니다.
        val jogGlobalBt = defBinding.jogGlobalBt
        val jogLocalBt = defBinding.jogLocalBt
        val jogUserBt = defBinding.jogUserBt
        val jogJointBt = defBinding.jogJointBt


        //조그 컨트롤러가 출력될 화면을 변수에 초기화 합니다.
        val jogViewer = defBinding.jogControllerView

        //우측 하단에 위치한 조그 컨트롤 화면을 바인딩 합니다. 해당 화면은 조그 모드에 따라서 바뀌게 됩니다.
        //jog Global
        val jgBinding = JGlobalActivityBinding.inflate(layoutInflater)
        val jlBinding = JLocalActivityBinding.inflate(layoutInflater)
        val jUbinding = JUserActivityBinding.inflate(layoutInflater)
        val jJbinding = JJointActivityBinding.inflate(layoutInflater)


        /* TREE를 제외한 우측에 요소들을 표시해주는 View 와 기본적으로 보여지는 화면(Default) 설정이며, 확장 버튼 클릭 시 ext Activity로 교체합니다. */
        val sideViewer = binding.sideView
        sideViewer.removeAllViews()
        sideViewer.addView(defBinding.root)

        val makePowerBt = binding.makePowerBt
        val treeExtBt = binding.treeExtBt

        //Ext Activity 바인딩합니다.
        val extBinding = MakeExtActivityBinding.inflate(layoutInflater)


        //Ext tree 토글 버튼 클릭 시 동작입니다.
        treeExtBt.setOnCheckedChangeListener{_,isChecked->
            if(isChecked){
                sideViewer.removeAllViews()
                sideViewer.addView(extBinding.root)

            }
            else{
                sideViewer.removeAllViews()
                sideViewer.addView(defBinding.root)
            }
        }


        //Jog Mode(Global, Local, User, Joint)를 선택하기 위한 버튼 동작을 정의
        jogGlobalBt.setOnClickListener{
            //다중 클릭 방지를 위해 한 번 클릭된 본인은 다른 버튼을 클릭 하기 전 까지 disable 시킵니다.
            jogGlobalBt.isEnabled = false

            //자신을 제외한 다른 버튼을 활성화 시킵니다.
            jogLocalBt.isEnabled = true
            jogUserBt.isEnabled = true
            jogJointBt.isEnabled = true

            jogViewer.removeAllViews()
            jogViewer.addView(jgBinding.root)
        }

        jogLocalBt.setOnClickListener{
            //다중 클릭 방지를 위해 한 번 클릭된 본인은 다른 버튼을 클릭 하기 전 까지 disable 시킵니다.
            jogLocalBt.isEnabled = false

            //자신을 제외한 다른 버튼을 활성화 시킵니다.
            jogGlobalBt.isEnabled =true
            jogUserBt.isEnabled = true
            jogJointBt.isEnabled = true


            jogViewer.removeAllViews()
            jogViewer.addView(jlBinding.root)
        }

        jogUserBt.setOnClickListener{
            //다중 클릭 방지를 위해 한 번 클릭된 본인은 다른 버튼을 클릭 하기 전 까지 disable 시킵니다.
            jogUserBt.isEnabled = false
            //자신을 제외한 다른 버튼을 활성화 시킵니다.
            jogGlobalBt.isEnabled = true
            jogLocalBt.isEnabled = true
            jogJointBt.isEnabled = true

            jogViewer.removeAllViews()
            jogViewer.addView(jUbinding.root)
        }

        jogJointBt.setOnClickListener{
            //다중 클릭 방지를 위해 한 번 클릭된 본인은 다른 버튼을 클릭 하기 전 까지 disable 시킵니다.
            jogJointBt.isEnabled = false
            //자신을 제외한 다른 버튼을 활성화 시킵니다.
            jogGlobalBt.isEnabled = true
            jogLocalBt.isEnabled = true
            jogUserBt.isEnabled = true

            jogViewer.removeAllViews()
            jogViewer.addView(jJbinding.root)
        }


        makeSmoothBt.setOnClickListener{
            //중복 클릭 방지를 위해 본인 버튼을 비활성화 시킵니다.
            makeSmoothBt.isEnabled = false
            makeSmoothBt.setBackgroundResource(R.drawable.color_red_box)

            makeTickBt.isEnabled = true
            makeTickBt.setBackgroundResource(R.drawable.square_background_border)

            //Tick에 관련된 요소들이 보이지 않게 가립니다.
            tickDist.isInvisible = true
            tickJoint.isInvisible = true
            tickOri.isInvisible = true
            jogModeTv1.isInvisible = true
            jogModeTv2.isInvisible = true
            jogModeTv3.isInvisible = true
        }

        makeTickBt.setOnClickListener{
            //중복 클릭 방지를 위해 본인 버튼을 비활성화 시킵니다.
            makeTickBt.isEnabled = false
            makeTickBt.setBackgroundResource(R.drawable.color_red_box)

            makeSmoothBt.isEnabled = true
            makeSmoothBt.setBackgroundResource(R.drawable.square_background_border)

            //Tick에 관련된 요소들을 활성화 시킵니다.
            tickDist.isVisible = true
            tickJoint.isVisible = true
            tickOri.isVisible = true
            jogModeTv1.isVisible = true
            jogModeTv2.isVisible= true
            jogModeTv3.isVisible = true
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