package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.JogFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState
import org.w3c.dom.Text

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


        //MakeDefaultFragment 에서 Global, Local, User, Joint  를 누를 때 UI 동작 코드입니다.
        // JOINT 모드일 경우 INFO 창을 JOINT1~4로 바꾸고 5~6번 버튼과 정보창을 Invisible 합니다.
        if(JogState.jogSelected == JogState.JOG_JOINT_SELECTED) {
            for (i in jogInfoList.indices) {
                jogInfoList[i].setText(jointStrList[i])
            }
            jogDec5.isVisible = false
            jogInc5.isVisible =false

            jogDec6.isVisible = false
            jogInc6.isVisible =false

            jogInfo5.isVisible = false
            jogInfo6.isVisible = false

            jogView5.isVisible = false
            jogView6.isVisible = false


        }
        else{
            //아닌 경우 INFO를 좌표계 문자열로 바꾸고 5~6번 버튼과 정보창을 Visible 합니다.
            for(i in jogInfoList.indices){
                jogInfoList[i].setText(coordStrList[i])

                jogDec5.isVisible = true
                jogInc5.isVisible = true

                jogDec6.isVisible = true
                jogInc6.isVisible = true

                jogInfo5.isVisible = true
                jogInfo6.isVisible = true

                jogView5.isVisible = true
                jogView6.isVisible = true

            }
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