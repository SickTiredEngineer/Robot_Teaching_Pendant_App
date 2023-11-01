package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.JogFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState
import com.example.robot_teaching_pendant_app.system.JogState.JOG_GLOBAL_SELECTED
import com.example.robot_teaching_pendant_app.system.JogState.JOG_JOINT_SELECTED
import com.example.robot_teaching_pendant_app.system.RobotState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = JogFragmentBinding.inflate(layoutInflater)

        val jogInfo1 = binding.jogInfo1
        val jogInfo2 = binding.jogInfo2
        val jogInfo3 = binding.jogInfo3
        val jogInfo4 = binding.jogInfo4
        val jogInfo5 = binding.jogInfo5
        val jogInfo6 = binding.jogInfo6

        //JogSelected 에 따라서 Info(TextView)를 바꿀 때 사용할 문자열 리소스 List
        val jogInfoList = listOf<TextView>(jogInfo1,jogInfo2,jogInfo3,jogInfo4,jogInfo5,jogInfo6)
        val coordStrList = listOf(R.string.str_x,R.string.str_y,R.string.str_z,R.string.str_rx,R.string.str_ry,R.string.str_rz)
        val jointStrList = listOf(R.string.str_base,R.string.str_should,R.string.str_elbow,R.string.str_wrist1,R.string.str_wrist2,R.string.str_wrist3)

        val jogView1 = binding.jogView1
        val jogView2 = binding.jogView2
        val jogView3 = binding.jogView3
        val jogView4 = binding.jogView4
        val jogView5 = binding.jogView5
        val jogView6 = binding.jogView6

        val jogInc1 = binding.jogInc1
        val jogInc2 = binding.jogInc2
        val jogInc3 = binding.jogInc3
        val jogInc4 = binding.jogInc4
        val jogInc5 = binding.jogInc5
        val jogInc6 = binding.jogInc6

        val jogDec1 = binding.jogDec1
        val jogDec2 = binding.jogDec2
        val jogDec3 = binding.jogDec3
        val jogDec4 = binding.jogDec4
        val jogDec5 = binding.jogDec5
        val jogDec6 = binding.jogDec6

        //Data Class robotState 객체 생성. 자세한 사항은 해당 Data Class 를 참고하세요.
        val robotState = RobotState()
//        val currentState = RobotStateManager.getRobotCurrentState()


        val incBtList = listOf<Button>(jogInc1,jogInc2,jogInc3,jogInc4,jogInc5,jogInc6)
        val decBtList = listOf<Button>(jogDec1,jogDec2,jogDec3,jogDec4,jogDec5,jogDec6)
        val jogViewList = listOf<EditText>(jogView1,jogView2,jogView3,jogView4,jogView5,jogView6)


        //JOINT 조그 사용 시, 사용하지 않는 버튼 리스트 입니다.
        val changeBtList = listOf<Button>(jogInc5,jogInc6,jogDec5,jogDec6)


        //MakeDefaultFragment 에서 Global, Local, User, Joint  를 누를 때 UI 동작 코드입니다.
        // JOINT 모드일 경우 INFO 창을 JOINT1~4로 바꾸고 5~6번 버튼과 정보창을 Invisible 합니다.
        if(JogState.jogSelected == JogState.JOG_JOINT_SELECTED) {
            for (i in jogInfoList.indices) {
                jogInfoList[i].setText(jointStrList[i])
                if (i > 3) {
                    jogInfoList[i].setBackgroundResource(R.drawable.color_gray_frame)
                    jogInfoList[i].isEnabled = false

                }

                for (j in changeBtList.indices) {
                    changeBtList[j].setBackgroundResource(R.drawable.color_gray_frame)
                    changeBtList[j].isEnabled = false
                }

                jogViewList[0].setText(robotState.joint1.toString())
                jogViewList[1].setText(robotState.joint2.toString())
                jogViewList[2].setText(robotState.joint3.toString())
                jogViewList[3].setText(robotState.joint4.toString())
                jogViewList[4].setText("-")
                jogViewList[5].setText("-")




                jogView5.isEnabled = false
                jogView5.setBackgroundResource(R.drawable.color_gray_frame)

                jogView6.isEnabled = false
                jogView6.setBackgroundResource(R.drawable.color_gray_frame)


            }
        }

        else{
                //아닌 경우 INFO를 좌표계 문자열로 바꾸고 5~6번 버튼과 정보창을 Visible 합니다.
                for (i in jogInfoList.indices) {
                    jogInfoList[i].setText(coordStrList[i])
                    if (i > 3) {
                        jogInfoList[i].setBackgroundResource(R.drawable.public_button)
                        jogInfoList[i].isEnabled = true

                    }
                }

                for (j in changeBtList.indices) {

                    changeBtList[j].setBackgroundResource(R.drawable.public_button)
                    changeBtList[j].isEnabled = true

                }

                jogViewList[0].setText(robotState.x.toString())
                jogViewList[1].setText(robotState.y.toString())
                jogViewList[2].setText(robotState.z.toString())
                jogViewList[3].setText(robotState.Rx.toString())
                jogViewList[4].setText(robotState.Ry.toString())
                jogViewList[5].setText(robotState.Rz.toString())

                jogView5.isEnabled = true
                jogView5.setBackgroundResource(R.drawable.public_button)

                jogView6.isEnabled = true
                jogView6.setBackgroundResource(R.drawable.public_button)
            }




        //JOG의 상승 버튼 리스너
        incBtList.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (JogState.jogSelected) {
                    JOG_GLOBAL_SELECTED -> {
                        when (index) {
                            0 -> {
                                robotState.x += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.x))
                            }
                            1 -> {
                                robotState.y += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.y))
                            }
                            2 -> {
                                robotState.z += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.z))
                            }
                            3 -> {
                                robotState.Rx += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.Rx))
                            }
                            4 -> {
                                robotState.Ry += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.Ry))
                            }
                            5 -> {
                                robotState.Rz += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.Rz))
                            }
                        }
                    }
                    JOG_JOINT_SELECTED -> {
                        when (index) {
                            0 -> {
                                robotState.joint1 += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint1))
                            }
                            1 -> {
                                robotState.joint2 += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint2))
                            }
                            2 -> {
                                robotState.joint3 += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint3))
                            }
                            3 -> {
                                robotState.joint4 += 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint4))
                            }
                            // 4, 5는 해당 관절값이 없으므로 아무 처리도 하지 않습니다.
                        }
                    }
                }
//                jogViewList[index].setText("%.2f".format(robotState))
            }
        }


        //Jog의 감소 버튼 리스너
        decBtList.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (JogState.jogSelected) {
                    JOG_GLOBAL_SELECTED -> {
                        when (index) {
                            0 -> {
                                robotState.x -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.x))

                            }
                            1 -> {
                                robotState.y -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.y))
                            }
                            2 -> {
                                robotState.z -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.z))
                            }
                            3 -> {
                                robotState.Rx -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.Rx))
                            }
                            4 -> {
                                robotState.Ry -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.Ry))
                            }
                            5 -> {
                                robotState.Rz -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.Rz))
                            }
                        }
                    }
                    JOG_JOINT_SELECTED -> {
                        when (index) {
                            0 -> {
                                robotState.joint1 -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint1))
                            }
                            1 -> {
                                robotState.joint2 -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint2))
                            }
                            2 -> {
                                robotState.joint3 -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint3))
                            }
                            3 -> {
                                robotState.joint4 -= 0.1f
                                jogViewList[index].setText("%.2f".format(robotState.joint4))
                            }
                            // 4, 5는 해당 관절값이 없으므로 아무 처리도 하지 않습니다.
                        }
                    }

                }
            }
        }



        //EditText 의 값을 수정하면 좌표계 혹은 관절 값으로 반영합니다.
        for (i in jogViewList.indices) {
            jogViewList[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    try {
                        val newValue = s.toString().toFloat()

                        if (JogState.jogSelected == JogState.JOG_GLOBAL_SELECTED) {
                            when (i) {
                                0 -> robotState.x = newValue
                                1 -> robotState.y = newValue
                                2 -> robotState.z = newValue
                                3 -> robotState.Rx = newValue
                                4 -> robotState.Ry = newValue
                                5 -> robotState.Rz = newValue
                            }
                        } else if (JogState.jogSelected == JogState.JOG_JOINT_SELECTED) {
                            when (i) {
                                0 -> robotState.joint1 = newValue
                                1 -> robotState.joint2 = newValue
                                2 -> robotState.joint3 = newValue
                                3 -> robotState.joint4 = newValue
                                // 4, 5번은 비활성화되므로 값 변경 로직은 필요하지 않습니다.
                            }
                        }

                    } catch (e: NumberFormatException) {
                        // 입력된 값이 유효한 float 값이 아닐 때 예외 처리
                    }
                }
            })
        }



        for (editText in jogViewList) {
            // CRLF 방지
            editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 개행 키를 누르면 아무런 동작을 하지 않습니다.
                    return@OnKeyListener true
                }
                false
            })
        }



        // Inflate the layout for this fragment
        return binding.root
    }






    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment jogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}