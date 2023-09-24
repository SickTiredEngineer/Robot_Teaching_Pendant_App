package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.MakeDefaultFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MakeDefaultFragment : Fragment() {
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


        val defBinding = MakeDefaultFragmentBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment


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

        val jogButtonList = listOf(jogGlobalBt,jogLocalBt,jogUserBt,jogJointBt)

        //Jogstate Object에 있는 jogSelected 값에 따라서 버튼의 Disable 상태를 결정합니다.
        when(JogState.jogSelected){
            JogState.JOG_GLOBAL_SELECTED->{
                jogGlobalBt.setBackgroundResource(R.drawable.color_gray_box)
                jogGlobalBt.isEnabled = false
            }
            JogState.JOG_LOCAL_SELECTED->{
                jogLocalBt.setBackgroundResource(R.drawable.color_gray_box)
                jogLocalBt.isEnabled = false
            }
            JogState.JOG_USER_SELECTED->{
                jogUserBt.setBackgroundResource(R.drawable.color_gray_box)
                jogUserBt.isEnabled = false
            }
            JogState.JOG_JOINT_SELECTED->{
                jogJointBt.setBackgroundResource(R.drawable.color_gray_box)
                jogJointBt.isEnabled = false
            }
        }

        //버튼을 클릭했을 때 본인을 Disable하고 색상을 바꾸며, 본인을 제외한 나머지 버튼들은 Enable 시키는 함수입니다.
        fun Button.assignJogState(value: Int, buttonList: List<Button>) {
            this.setOnClickListener {
                JogState.jogSelected = value
                for (otherButton in buttonList) {
                    if (otherButton == this) {
                        otherButton.isEnabled = false
                        otherButton.setBackgroundResource(R.drawable.color_gray_box) // 회색 배경 리소스

                        val buttonText = otherButton.text
                        Toast.makeText(it.context, "$buttonText 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        otherButton.isEnabled = true
                        otherButton.setBackgroundResource(R.drawable.square_background_border) // 기본 배경 리소스
                    }
                }
            }
        }

        //조그를 선택하는 4가지 버튼을 , 위의 함수를 활용하여 그에 맞는 모드와 UI 로직을 가지게 합니다.
        jogGlobalBt.assignJogState(JogState.JOG_GLOBAL_SELECTED, jogButtonList)
        jogLocalBt.assignJogState(JogState.JOG_LOCAL_SELECTED, jogButtonList)
        jogUserBt.assignJogState(JogState.JOG_USER_SELECTED, jogButtonList)
        jogJointBt.assignJogState(JogState.JOG_JOINT_SELECTED, jogButtonList)


        //Jog Controller를 삽입 할 보여 줄 View 입니다.
        val jogViewer = defBinding.jogControllerView

        if (savedInstanceState == null) {
            val fragment = JogFragment()
            childFragmentManager.beginTransaction()
                .replace(jogViewer.id, fragment)
                .commit()
        }

        //Activity 에서도 접근 하려면 필요한 코드, 임시로 남겨 둠
//        if (savedInstanceState == null) {
//            val fragment = JogFragment()
//            activity?.supportFragmentManager?.beginTransaction()
//                .replace(jogViewer.id, fragment)
//                .commit()
//        }


        when(JogState.jogModeSelected){
            JogState.JOG_SMOOTH_SELECTED->{
                makeSmoothBt.setBackgroundResource(R.drawable.color_red_box)
                makeSmoothBt.isEnabled=false
            }

            JogState.JOG_TICK_SELECTED-> {
                makeTickBt.setBackgroundResource(R.drawable.color_red_box)
                makeTickBt.isEnabled = false

                tickDist.isVisible = true
                tickJoint.isVisible = true
                tickOri.isVisible = true
                jogModeTv1.isVisible = true
                jogModeTv2.isVisible= true
                jogModeTv3.isVisible = true
            }
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

            JogState.jogModeSelected = JogState.JOG_SMOOTH_SELECTED
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

            JogState.jogModeSelected = JogState.JOG_TICK_SELECTED
        }

        return defBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MakeDefault.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MakeDefaultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}