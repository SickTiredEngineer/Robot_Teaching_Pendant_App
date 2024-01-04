package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
class JogFragment : Fragment(){


    //해야 할 것: InterFace 정리

    interface GoHomeListener {
        fun onGoHome()
    }
    var listener: GoHomeListener? = null


    interface RefreshEtListener {
        fun refreshET()
    }
    var etListener: RefreshEtListener? = null


    interface RefreshJogListener{
        fun refreshJog()
    }


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
    ): View {



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
        val coordStrList = listOf(R.string.str_x, R.string.str_y, R.string.str_z, R.string.str_rx, R.string.str_ry, R.string.str_rz)

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

        //선택된 조그에 따라 상태가 바뀌게 되는 버튼들을 모아놓은 리스트입니다.
        val changeBtList = listOf<Button>(jogInc5, jogInc6, jogDec5, jogDec6)


        //작업환경(Make)을 호출하면, 현재 프로그램에서 선택된 조그에 따라 Jog를 세팅합니다.
        //안전한 UI 업데이트를 위해, UI THREAD에서 조그를 세팅하는 함수를 호출합니다.
        activity?.runOnUiThread {
            setJog()
        }


        //JOG의 상승 버튼 리스너
        incBtList.forEachIndexed { index, button ->
            button.setOnClickListener {

                when (jogSelected) {
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
                refreshViewerTextView()

            }

            val handler = Handler()

            val autoIncrementRunnable = object : Runnable {
                override fun run() {
                    when (jogSelected) {
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

                    refreshViewerTextView()

                    // Handler를 사용하여 자기 자신을 0.1초 후에 다시 실행하도록 합니다.
                    handler.postDelayed(this, 100)  // 0.1초 후에 다시 실행
                }
            }


            button.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // 버튼이 눌려졌을 때 Runnable 객체를 실행합니다.
                        handler.post(autoIncrementRunnable)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        // 버튼에서 손을 떼거나 취소되었을 때 Runnable 객체의 실행을 중단합니다.
                        handler.removeCallbacks(autoIncrementRunnable)
                    }
                }
                true  // 이벤트가 처리되었음을 나타냅니다.
            }
        }


        //Jog의 감소 버튼 리스너
        decBtList.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (jogSelected) {
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

                refreshViewerTextView()
            }

            val handler = Handler()

            val autoDecrementRunnable = object : Runnable {
                override fun run() {
                    when (jogSelected) {
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

                    refreshViewerTextView()

                    // Handler를 사용하여 자기 자신을 0.1초 후에 다시 실행하도록 합니다.
                    handler.postDelayed(this, 100)  // 0.1초 후에 다시 실행
                }
            }

            button.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // 버튼이 눌려졌을 때 Runnable 객체를 실행합니다.
                        handler.post(autoDecrementRunnable)

                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        // 버튼에서 손을 떼거나 취소되었을 때 Runnable 객체의 실행을 중단합니다.
                        handler.removeCallbacks(autoDecrementRunnable)

                    }
                }
                true  // 이벤트가 처리되었음을 나타냅니다.
            }
        }


//        //유효값이 아닐 경우를 대비한 복원 배열입니다.(현재 사용x)
//        val previousValues = Array(jogViewList.size) { "" }
//        val isUserInput = Array(jogViewList.size) { true }


        //Jog의 값을 입력하는 EditText들에 관련된 설정입니다.
        //EditText에 값을 입력 시, 값의 범위를 확인하고 유효한 값이면 갱신, 아닐 시 복원합니다.
        for (i in jogViewList.indices) {
            jogViewList[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(s: Editable) {}
            })

            //소프트 키보드의 엔터를 클릭 시, handleInput 메서드를 실행하여 동작을 수행합니다.
            jogViewList[i].setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {

                    //handleInput함수는 Jog에 입력된 값의 범위를 확인하여 반영하거나 되돌리는 역할을 합니다.
                    //자세한 로직은 아래의 handle Input 함수를 참고하십시요.
                    handleInput(i,jogViewList, jogViewList[i].text.toString())
                    refreshEditText()

                    refreshViewerTextView()

                    true // 이벤트 처리 완료
                }
                else {


                    false // 이벤트 처리 안 함
                }
            }

            //개행 방지 코드입니다.
            jogViewList[i].setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
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
    private fun increaseValueAndSet(maxValue: Float, currentValue: Float, increment: Float): Float {
        val newValue = currentValue + increment
        return newValue.coerceAtMost(maxValue)
    }


    //Jog의 - 버튼을 눌렀을때 최소 값을 지정하고, 이 이하가 될 경우 값을 감소시키지 않습니다.
    private fun decreaseValueAndSet(minValue: Float, currentValue: Float, decrement: Float): Float {
        val newValue = currentValue - decrement
        return newValue.coerceAtLeast(minValue)
    }






    //EditText 값을 선택된 조그에 맞게, 다시 불러올때 사용하는 함수입니다.
    fun refreshEditText(){
        when (jogSelected) {
            JOG_JOINT_SELECTED -> {
                binding.jogView1.setText("%.2f".format(RobotPosition.joint1))
                binding.jogView2.setText("%.2f".format(RobotPosition.joint2))
                binding.jogView3.setText("%.2f".format(RobotPosition.joint3))
                binding.jogView4.setText("%.2f".format(RobotPosition.joint4))
                binding.jogView5.setText("-")
                binding.jogView6.setText("-")
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

    //makeDefaultFragment에서 Quick Home 버튼을 클릭 시, 로봇을 영점으로 움직이는 명령어입니다. 이름 수정 예정
    fun goHome() {
        Toast.makeText(context, "로봇을 영점으로 이동시킵니다", Toast.LENGTH_SHORT).show()

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

        //EditText를 새로고침 하는 함수입니다.
        refreshEditText()

        refreshViewerTextView()
    }


    fun setJog(){
        val incBtList = listOf<Button>(binding.jogInc1, binding.jogInc2, binding.jogInc3, binding.jogInc4, binding.jogInc5, binding.jogInc6)
        val decBtList = listOf<Button>(binding.jogDec1, binding.jogDec2, binding.jogDec3, binding.jogDec4, binding.jogDec5, binding.jogDec6)
        val jogViewList = listOf<EditText>(binding.jogView1, binding.jogView2, binding.jogView3, binding.jogView4, binding.jogView5, binding.jogView6)

        val jogInfoList = listOf<TextView>(binding.jogInfo1, binding.jogInfo2, binding.jogInfo3, binding.jogInfo4,binding. jogInfo5, binding.jogInfo6)
        val coordStrList = listOf(R.string.str_x, R.string.str_y, R.string.str_z, R.string.str_rx, R.string.str_ry, R.string.str_rz)
        //관절 값 리소스 이름 수정 필요.
        val jointStrList = listOf(R.string.str_joint1, R.string.str_joint2, R.string.str_joint3, R.string.str_joint4, R.string.str_joint_null1, R.string.str_joint_null2)

        //선택된 조그에 따라 상태가 바뀌게 되는 버튼들을 모아놓은 리스트입니다.
        val changeBtList = listOf<Button>(binding.jogInc5, binding.jogInc6, binding.jogDec5, binding.jogDec6)


        //JOINT 조그가 선택되어 있는 경우 수행할 UI 동작입니다.
        if (jogSelected == JOG_JOINT_SELECTED) {
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

                binding.jogView5.isEnabled = false
                binding.jogView5.setBackgroundResource(R.drawable.color_gray_frame)

                binding.jogView6.isEnabled = false
                binding.jogView6.setBackgroundResource(R.drawable.color_gray_frame)

                //EditText를 새로고침 하는 함수입니다.
                refreshEditText()
            }
        }

        //GLOBAL 조그가 선택되어 있는 경우 수행할 UI 동작입니다.
        else if(jogSelected== JOG_GLOBAL_SELECTED){
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

            binding.jogView5.isEnabled = true
            binding.jogView5.setBackgroundResource(R.drawable.public_button)

            binding.jogView6.isEnabled = true
            binding.jogView6.setBackgroundResource(R.drawable.public_button)

            //EditText를 새로고침 하는 함수입니다.
            refreshEditText()
        }
    }




    //EditText에 입력된 값이 유효한지(0~360) 검사하고, 해당 값을 로봇 위치를 저장하는 RobotPosition 전역변수에 저장합니다.
    private fun handleInput(index: Int,array: List<EditText>, input: String) {
        try {
            val newValue = input.toFloat()

            //EditText에 입력된 값이 유효할 경우, 해당 값을 RobotPosition에 대입합니다.
            if (newValue in 0.0..360.0) {
                //선택된 조그가 GLOBAL 일때
                if (jogSelected == JOG_GLOBAL_SELECTED) {
                    //0(x), 1(y)... 5(Rz) 순서입니다.
                    when(index) {
                        0 ->{
                            RobotPosition.x = newValue
                            Toast.makeText(context, "X좌표를 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        1 ->{
                            RobotPosition.y = newValue
                            Toast.makeText(context, "Y좌표를 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        2 ->{
                            RobotPosition.z = newValue
                            Toast.makeText(context, "Z좌표를 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        3 ->{
                            RobotPosition.Rx = newValue
                            Toast.makeText(context, "Rx좌표를 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        4 ->{
                            RobotPosition.Ry = newValue
                            Toast.makeText(context, "Ry좌표를 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        5 ->{
                            RobotPosition.Rz = newValue
                            Toast.makeText(context, "Rz좌표를 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                //선택된 조그가 JOINT 일때
                else if (jogSelected == JOG_JOINT_SELECTED) {
                    when (index) {
                        //0(joint1)~3(joint4) 순서입니다.
                        0 ->{
                            RobotPosition.joint1 = newValue
                            Toast.makeText(context, "joint1을 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        1 ->{
                            RobotPosition.joint2 = newValue
                            Toast.makeText(context, "joint2을 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        2 ->{
                            RobotPosition.joint3 = newValue
                            Toast.makeText(context, "joint3을 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        3 ->{
                            RobotPosition.joint4 = newValue
                            Toast.makeText(context, "joint4을 $newValue 로 이동시킵니다.", Toast.LENGTH_LONG).show()
                        }
                        // 4, 5번은 비활성화되므로 값 변경 로직은 필요하지 않습니다.

                    }
                }
            }

            //0~360이 아닐 경우, 적용 시키지 않습니다. (EditText를 변경 전으로 돌려놓습니다.
            //선택된 조그가 GLOBAL && 유효한 값이 아닐 때 동작입니다. RobotPosition이 바뀌지 않았음으로, EditText에 다시 해당 값을 반영합니다.
            else {
                if (jogSelected == JOG_GLOBAL_SELECTED) {
                    when (index) {
                        //0(x), 1(y)... 5(Rz) 순서입니다.
                        0 -> {
                            RobotPosition.x = RobotPosition.x
                            array[index].setText("%.2f".format(RobotPosition.x))
                            Toast.makeText(context, " X좌표의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        1 -> {
                            RobotPosition.y = RobotPosition.y
                            array[index].setText("%.2f".format(RobotPosition.y))
                            Toast.makeText(context, " Y좌표의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        2 -> {
                            RobotPosition.z = RobotPosition.z
                            array[index].setText("%.2f".format(RobotPosition.z))
                            Toast.makeText(context, " Z좌표의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        3 -> {
                            RobotPosition.Rx = RobotPosition.Rx
                            array[index].setText("%.2f".format(RobotPosition.Rx))
                            Toast.makeText(context, " Rx좌표의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        4 -> {
                            RobotPosition.Ry = RobotPosition.Ry
                            array[index].setText("%.2f".format(RobotPosition.Ry))
                            Toast.makeText(context, " Ry좌표의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        5 -> {
                            RobotPosition.Rz = RobotPosition.Rz
                            array[index].setText("%.2f".format(RobotPosition.Rz))
                            Toast.makeText(context, " Rz좌표의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                //선택된 조그가 JOINT && 유효한 값이 아닐 때 동작입니다. RobotPosition이 바뀌지 않았음으로, EditText에 다시 해당 값을 반영합니다.
                else if (jogSelected == JogState.JOG_JOINT_SELECTED) {
                    when (index) {
                        //0(joint1)~3(joint4) 순서입니다.
                        0 -> {
                            RobotPosition.joint1 = RobotPosition.joint1
                            array[index].setText("%.2f".format(RobotPosition.joint1))
                            Toast.makeText(context, " Joint1의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        1 -> {
                            RobotPosition.joint2 = RobotPosition.joint2
                            array[index].setText("%.2f".format(RobotPosition.joint2))
                            Toast.makeText(context, " Joint2의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        2 -> {
                            RobotPosition.joint3 = RobotPosition.joint3
                            array[index].setText("%.2f".format(RobotPosition.joint3))
                            Toast.makeText(context, " Joint3의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }

                        3 -> {
                            RobotPosition.joint4 = RobotPosition.joint4
                            array[index].setText("%.2f".format(RobotPosition.joint4))
                            Toast.makeText(context, " Joint4의 $newValue 는 유효한 값이 아닙니다. 실행을 취소합니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            // 유효하지 않은 값일 경우의 처리
        } catch (e: NumberFormatException) {
            // 입력된 값이 유효한 float 값이 아닐 때 예외 처리
        }
    }


    fun refreshViewerTextView(){
        (parentFragment as? PositionViewerFragment.FragmentCommunicationInterface)?.refreshTextView()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴될 때 binding을 해제합니다.

        _binding = null
    }
}

