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
import com.example.robot_teaching_pendant_app.databinding.LoginActivityBinding
import com.example.robot_teaching_pendant_app.system.PowerOffDialogFragment
import org.yaml.snakeyaml.Yaml
import java.io.File


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //비밀번호가 들어갈 EditText 4개를 변수에 초기화 시킵니다. 순서는 제일 좌측이 1번이며 4번까지 존재합니다.
        val passBox1 = binding.passBox1
        val passBox2 = binding.passBox2
        val passBox3 = binding.passBox3
        val passBox4 = binding.passBox4

        //자동으로 숫자를 입력할 때 마다 Focus 를 바꿔주는 함수입니다. 제일 아래 (onCreate 밖) 에 구현 해 놓았습니다.
        setupAutoFocusEditTexts(passBox1, passBox2, passBox3, passBox4)


        //변수에 위젯 초기화
        val loginBt = binding.loginBt
        val loginPowerBt = binding.loginPowerBt
        val loginLoadingBar = binding.loginLoadingBar


        // 메인 메뉴로 이동
        loginBt.setOnClickListener{
            val enteredPassword = arrayOf(passBox1, passBox2, passBox3, passBox4).joinToString(separator = "") { it.text.toString() }
            val savedPassword = readPasswordFromYaml()

            if (enteredPassword == savedPassword) {
                val nextIntent = Intent(this, MainActivity::class.java)
                startActivity(nextIntent)
                Toast.makeText(this@LoginActivity, "로그인 완료", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
            }
        }



        //우측 하단에 위치한 전원 버튼을 누를 시, PowerOffDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 System 디렉토리의 PowerOffDialogFragment를 참고하십시요.
        loginPowerBt.setOnClickListener{
            val dialogFragment = PowerOffDialogFragment()
            dialogFragment.show(supportFragmentManager,null)

        }

    }

    private fun readPasswordFromYaml(): String {
        val yaml = Yaml()
        val file = File(filesDir, "password.yaml")
        // 파일이 존재하지 않으면 초기 설정 생성
        if (!file.exists()) {
            val initialConfig = mapOf("password" to "0000")
            file.writeText(yaml.dump(initialConfig))
            return "0000"
        }
        // 파일이 존재하면 설정 읽기
        val inputStream = file.inputStream()
        val data = yaml.load<Map<String, Any>>(inputStream)
        return data["password"] as? String ?: "0000" // 기본값으로 '0000'을 반환
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

            //현재의 EditText가 지워질 때 (내용이 제거될 때) 이전 EditText로 포커스가 이동하길 원한다면 사용하는 코드
            editTexts[i].setOnKeyListener { _, _, event ->
                if (event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL && editTexts[i].text.isEmpty() && i - 1 >= 0) {
                    editTexts[i - 1].requestFocus()
                }
                false
            }
        }
    }


}



