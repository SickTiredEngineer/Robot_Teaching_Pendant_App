package com.example.robot_teaching_pendant_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
//import android.view.View
//import android.view.WindowManager
//import android.widget.Button
import android.widget.Toast
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.databinding.LoginActivityBinding



class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //비밀번호가 들어갈 EditText 4개를 변수에 초기화 시킵니다. 순서는 제일 좌측이 1번이며 4번까지 존재합니다.
        val passBox1 = binding.passBox1
        val passBox2 = binding.passBox2
        val passBox3 = binding.passBox3
        val passBox4 = binding.passBox4

        //자동으로 숫자를 입력할 때 마다 Focus 를 바꿔주는 함수입니다. 제일 아래에 구현 해 놓았습니다.
        setupAutoFocusEditTexts(passBox1, passBox2, passBox3, passBox4)



        //변수에 위젯 초기화
        val loginBt = binding.loginBt
        val loginPowerBt = binding.loginPowerBt
        val loginLoadingBar = binding.loginLoadingBar



        // 메인 메뉴로 이동
        loginBt.setOnClickListener{
            val nextIntent = Intent(this, MainActivity::class.java)

            //시스템 변수 전송 내용 추후 추가

            startActivity(nextIntent)
            Toast.makeText(this@LoginActivity, "로그인 완료", Toast.LENGTH_SHORT ).show()
            //로그인 화면 종료
            finish()

        }

        loginPowerBt.setOnClickListener{
            Toast.makeText(this@LoginActivity,"전원 버튼 클릭",Toast.LENGTH_SHORT).show()
        }

    }
}



//EditText에 숫자를 작성하면 자동으로 Focus를 바꿔주는 함수입니다.
private fun setupAutoFocusEditTexts(vararg editTexts: EditText) {
    for (i in editTexts.indices) {
        editTexts[i].addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // no-op
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length == 1 && i + 1 < editTexts.size) { // if current EditText has 1 character and is not the last one
                    editTexts[i + 1].requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {
                // no-op
            }
        })

        // Optional: If you want the previous EditText to be focused when current EditText is cleared.
        editTexts[i].setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL && editTexts[i].text.isEmpty() && i - 1 >= 0) {
                editTexts[i - 1].requestFocus()
            }
            false
        }
    }
}