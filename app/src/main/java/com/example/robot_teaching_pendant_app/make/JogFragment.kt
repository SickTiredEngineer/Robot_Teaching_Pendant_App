package com.example.robot_teaching_pendant_app.make

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.JogFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState
import com.example.robot_teaching_pendant_app.system.JogState.JOG_GLOBAL_SELECTED
import com.example.robot_teaching_pendant_app.system.JogState.JOG_JOINT_SELECTED
import com.example.robot_teaching_pendant_app.system.JogState.jogSelected
import com.example.robot_teaching_pendant_app.system.RobotPosition

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

    private var _binding: JogFragmentBinding? = null
    private val binding get() = _binding!!

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

        _binding = JogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jogInfo1 = binding.jogInfo1
        val jogInfo2 = binding.jogInfo2
        val jogInfo3 = binding.jogInfo3
        val jogInfo4 = binding.jogInfo4
        val jogInfo5 = binding.jogInfo5
        val jogInfo6 = binding.jogInfo6

        //JogSelected 에 따라서 Info(TextView)를 바꿀 때 사용할 문자열 리소스 List
        val jogInfoList =
            listOf<TextView>(jogInfo1, jogInfo2, jogInfo3, jogInfo4, jogInfo5, jogInfo6)
        val coordStrList = listOf(
            R.string.str_x,
            R.string.str_y,
            R.string.str_z,
            R.string.str_rx,
            R.string.str_ry,
            R.string.str_rz
        )

        //관절 값 리소스 이름 수정 필요.
        val jointStrList = listOf(R.string.str_joint1, R.string.str_joint2, R.string.str_joint3, R.string.str_joint4, R.string.str_joint_null1, R.string.str_joint_null2)

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


        //로직에 사용될 버튼과 EditText의 리스트입니다.
        val incBtList = listOf<Button>(jogInc1, jogInc2, jogInc3, jogInc4, jogInc5, jogInc6)
        val decBtList = listOf<Button>(jogDec1, jogDec2, jogDec3, jogDec4, jogDec5, jogDec6)
        val jogViewList = listOf<EditText>(jogView1, jogView2, jogView3, jogView4, jogView5, jogView6)


        //JOINT 조그 사용 시, 사용하지 않는 버튼 리스트 입니다.
        val changeBtList = listOf<Button>(jogInc5, jogInc6, jogDec5, jogDec6)


        //MakeDefaultFragment 에서 Global, Local, User, Joint  를 누를 때 UI 동작 코드입니다.
        //MakeDefaultFragment 에서 4가지 조그 중 하나를 선택하면, 해당 Fragment를 새로고침 하여 변경된 사항을 적용하게 됩니다.

        // JOINT 모드일 경우 INFO 창을 JOINT1~4로 바꾸고 5~6번 버튼과 정보창을 Invisible 합니다.
        if (JogState.jogSelected == JogState.JOG_JOINT_SELECTED) {
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

                jogViewList[0].setText(RobotPosition.joint1.toString())
                jogViewList[1].setText(RobotPosition.joint2.toString())
                jogViewList[2].setText(RobotPosition.joint3.toString())
                jogViewList[3].setText(RobotPosition.joint4.toString())
                jogViewList[4].setText("-")
                jogViewList[5].setText("-")

                jogView5.isEnabled = false
                jogView5.setBackgroundResource(R.drawable.color_gray_frame)

                jogView6.isEnabled = false
                jogView6.setBackgroundResource(R.drawable.color_gray_frame)


            }
        } else {
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

            jogViewList[0].setText(RobotPosition.x.toString())
            jogViewList[1].setText(RobotPosition.y.toString())
            jogViewList[2].setText(RobotPosition.z.toString())
            jogViewList[3].setText(RobotPosition.Rx.toString())
            jogViewList[4].setText(RobotPosition.Ry.toString())
            jogViewList[5].setText(RobotPosition.Rz.toString())

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
                        //감소 버튼 순서는 제일 위에 0번부터 마지막 제일 아래 5번까지 순서입니다.
                        when (index) {
                            0 -> {
                                RobotPosition.x = increaseValueAndSet(360.0f, RobotPosition.x, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.x))
                            }
                            1 -> {
                                RobotPosition.y = increaseValueAndSet(360.0f, RobotPosition.y, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.y))
                            }
                            2 -> {
                                RobotPosition.z = increaseValueAndSet(360.0f, RobotPosition.z, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.z))
                            }
                            3 -> {
                                RobotPosition.Rx = increaseValueAndSet(360.0f, RobotPosition.Rx, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.Rx))
                            }
                            4 -> {
                                RobotPosition.Ry = increaseValueAndSet(360.0f, RobotPosition.Ry, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.Ry))
                            }
                            5 -> {
                                RobotPosition.Rz = increaseValueAndSet(360.0f, RobotPosition.Rz, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.Rz))
                            }
                        }
                    }

                    //관절값은 4개만 필요하기 때문에 0~3까지만 사용합니다.
                    JOG_JOINT_SELECTED -> {
                        when (index) {
                            0 -> {
                                RobotPosition.joint1 = increaseValueAndSet(360.0f, RobotPosition.joint1, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint1))
                            }
                            1 -> {
                                RobotPosition.joint2 = increaseValueAndSet(360.0f, RobotPosition.joint2, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint2))
                            }
                            2 -> {
                                RobotPosition.joint3 = increaseValueAndSet(360.0f, RobotPosition.joint3, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint3))
                            }
                            3 -> {
                                RobotPosition.joint4 = increaseValueAndSet(360.0f, RobotPosition.joint4, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint4))
                            }
                            // 4, 5는 해당 관절값이 없으므로 아무 처리도 하지 않습니다.
                        }
                    }
                }
            }
        }


        //Jog의 감소 버튼 리스너
        decBtList.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (JogState.jogSelected) {
                    JOG_GLOBAL_SELECTED -> {
                        //감소 버튼 순서는 제일 위에 0번부터 마지막 제일 아래 5번까지 순서입니다.
                        when (index) {
                            0 -> {
                                RobotPosition.x = decreaseValueAndSet(0.0f, RobotPosition.x, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.x))
                            }
                            1 -> {
                                RobotPosition.y = decreaseValueAndSet(0.0f, RobotPosition.y, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.y))
                            }
                            2 -> {
                                RobotPosition.z = decreaseValueAndSet(0.0f, RobotPosition.z, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.z))
                            }
                            3 -> {
                                RobotPosition.Rx = decreaseValueAndSet(0.0f, RobotPosition.Rx, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.Rx))
                            }
                            4 -> {
                                RobotPosition.Ry = decreaseValueAndSet(0.0f, RobotPosition.Ry, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.Ry))
                            }
                            5 -> {
                                RobotPosition.Rz = decreaseValueAndSet(0.0f, RobotPosition.Rz, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.Rz))
                            }
                        }
                    }

                    //관절값은 4개만 필요하기 때문에 0~3까지만 사용합니다.
                    JOG_JOINT_SELECTED -> {
                        when (index) {
                            0 -> {
                                RobotPosition.joint1 = decreaseValueAndSet(0.0f, RobotPosition.joint1, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint1))
                            }
                            1 -> {
                                RobotPosition.joint2 = decreaseValueAndSet(0.0f, RobotPosition.joint2, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint2))
                            }
                            2 -> {
                                RobotPosition.joint3 = decreaseValueAndSet(0.0f, RobotPosition.joint3, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint3))
                            }
                            3 -> {
                                RobotPosition.joint4 = decreaseValueAndSet(0.0f, RobotPosition.joint4, 0.1f)
                                jogViewList[index].setText("%.2f".format(RobotPosition.joint4))
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
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    try {
                        val newValue = s.toString().toFloat()

                        //Global Jog 일 때, 각 좌표계 값에 입력값을 대입합니다.
                        if (JogState.jogSelected == JogState.JOG_GLOBAL_SELECTED && newValue in 0.0..360.0) {
                            when (i) {
                                0 -> RobotPosition.x = newValue
                                1 -> RobotPosition.y = newValue
                                2 -> RobotPosition.z = newValue
                                3 -> RobotPosition.Rx = newValue
                                4 -> RobotPosition.Ry = newValue
                                5 -> RobotPosition.Rz = newValue
                            }

                            //Joint Jog 일 때, 각 관절값에 입력값을 대입합니다.
                        } else if (JogState.jogSelected == JogState.JOG_JOINT_SELECTED && newValue in 0.0..360.0) {
                            when (i) {
                                0 -> RobotPosition.joint1 = newValue
                                1 -> RobotPosition.joint2 = newValue
                                2 -> RobotPosition.joint3 = newValue
                                3 -> RobotPosition.joint4 = newValue
                                // 4, 5번은 비활성화되므로 값 변경 로직은 필요하지 않습니다.
                            }
                        }

                    } catch (e: NumberFormatException) {
                        // 입력된 값이 유효한 float 값이 아닐 때 예외 처리
                    }
                }
            })
        }


        //JOG 수치를 입력하는 EditText에 CRLF를 허용하지 않습니다.
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



    //Jog의 + 버튼을 눌렀을때 최대 값을 지정하고, 이 이상이 될 경우 값을 상승시키지 않습니다.
    fun increaseValueAndSet(maxValue: Float, currentValue: Float, increment: Float): Float {
        val newValue = currentValue + increment
        return newValue.coerceAtMost(maxValue)
    }


    //Jog의 - 버튼을 눌렀을때 최소 값을 지정하고, 이 이하가 될 경우 값을 감소시키지 않습니다.
    fun decreaseValueAndSet(minValue: Float, currentValue: Float, decrement: Float): Float {
        val newValue = currentValue - decrement
        return newValue.coerceAtLeast(minValue)
    }


    //makeDefaultFragment에서 Quick Home 버튼을 클릭 시, 로봇을 영점으로 움직이는 명령어입니다. 이름 수정 예정
    fun goHome() {

        RobotPosition.joint1 = 0f
        RobotPosition.joint2 = 0f
        RobotPosition.joint3 = 0f
        RobotPosition.joint4 = 0f

        RobotPosition.x = 0f
        RobotPosition.y = 0f
        RobotPosition.z = 0f
        RobotPosition.Rx = 0f
        RobotPosition.Ry = 0f
        RobotPosition.Rz = 0f

        when (jogSelected) {
            JOG_JOINT_SELECTED -> {
                binding.jogView1.setText("%.2f".format(RobotPosition.joint1))
                binding.jogView2.setText("%.2f".format(RobotPosition.joint2))
                binding.jogView3.setText("%.2f".format(RobotPosition.joint3))
                binding.jogView4.setText("%.2f".format(RobotPosition.joint4))
            }

            JOG_GLOBAL_SELECTED -> {
                binding.jogView1.setText("%.2f".format(RobotPosition.x))
                binding.jogView2.setText("%.2f".format(RobotPosition.y))
                binding.jogView3.setText("%.2f".format(RobotPosition.z))
                binding.jogView4.setText("%.2f".format(RobotPosition.Rx))
                binding.jogView5.setText("%.2f".format(RobotPosition.Ry))
                binding.jogView6.setText("%.2f".format(RobotPosition.Rz))
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 binding을 해제합니다.

        _binding = null
    }
}

