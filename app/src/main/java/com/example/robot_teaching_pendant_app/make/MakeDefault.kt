package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.JGlobalActivityBinding
import com.example.robot_teaching_pendant_app.databinding.JJointActivityBinding
import com.example.robot_teaching_pendant_app.databinding.JLocalActivityBinding
import com.example.robot_teaching_pendant_app.databinding.JUserActivityBinding
import com.example.robot_teaching_pendant_app.databinding.MakeDefaultFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MakeDefault : Fragment() {
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

        //Jog Controller를 보여 줄 View 입니다.
        val jogViewer = defBinding.jogControllerView

            //Jog Mode(Global, Local, User, Joint)를 선택하기 위한 버튼 동작을 정의
        jogGlobalBt.setOnClickListener{
            //다중 클릭 방지를 위해 한 번 클릭된 본인은 다른 버튼을 클릭 하기 전 까지 disable 시킵니다.
            val jgBinding = JGlobalActivityBinding.inflate(layoutInflater)
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
            val jlBinding = JLocalActivityBinding.inflate(layoutInflater)
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
            val juBinding = JUserActivityBinding.inflate(layoutInflater)
            jogUserBt.isEnabled = false
            //자신을 제외한 다른 버튼을 활성화 시킵니다.
            jogGlobalBt.isEnabled = true
            jogLocalBt.isEnabled = true
            jogJointBt.isEnabled = true

            jogViewer.removeAllViews()
            jogViewer.addView(juBinding.root)
        }

        jogJointBt.setOnClickListener{
            //다중 클릭 방지를 위해 한 번 클릭된 본인은 다른 버튼을 클릭 하기 전 까지 disable 시킵니다.
            val jJBinding = JJointActivityBinding.inflate(layoutInflater)
            jogJointBt.isEnabled = false
            //자신을 제외한 다른 버튼을 활성화 시킵니다.
            jogGlobalBt.isEnabled = true
            jogLocalBt.isEnabled = true
            jogUserBt.isEnabled = true

            jogViewer.removeAllViews()
            jogViewer.addView(jJBinding.root)
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
            MakeDefault().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}