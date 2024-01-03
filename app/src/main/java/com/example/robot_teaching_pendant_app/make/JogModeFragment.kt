package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.JogModeFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JogModeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JogModeFragment : Fragment() {
    private var _binding:  JogModeFragmentBinding? = null
    private val binding get() = _binding!!



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

        _binding = JogModeFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


                //Jog Mode -> Smooth , Tick 을 선택할 때 사용하는 버튼입니다.
        val makeSmoothBt = binding.makeSmoothBt
        val makeTickBt = binding.makeTickBt


        //Tick 모드 활성화 시 나오는 EditText 입니다.
        val tickJoint = binding.tickJoint
        val tickOri = binding.tickOri
        val tickDist = binding.tickDist

        //Tick 모드 활성화 시 나오는 EditText 설명 TextView 입니다.
        val jogModeTv1 = binding.jogModeTv1
        val jogModeTv2 = binding.jogModeTv2
        val jogModeTv3 = binding.jogModeTv3

        //기본적으로 Tick 모드에서 사용하는 요소들은 가려지게 설정합니다.
        tickDist.isInvisible = true
        tickJoint.isInvisible = true
        tickOri.isInvisible = true
        jogModeTv1.isInvisible = true
        jogModeTv2.isInvisible = true
        jogModeTv3.isInvisible = true




        //초기 Fragment를 생성할 때, Tick Mode에 있는 3개의 EditText에 값을 반영합니다.
        tickDist.setText("%.2f".format(JogState.jogModeDist))
        tickOri.setText("%.2f".format(JogState.jogModeOri))
        tickJoint.setText("%.2f".format(JogState.jogModeJoint))



        // tickDist EditText에 대한 리스너 설정
        binding.tickDist.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val value = binding.tickDist.text.toString().toFloatOrNull()

                //다음 코드에서 Dist에 들어갈 값의 범위를 조절합니다.
                if (value != null && value in 0.0f..100.0f) {
                    JogState.jogModeDist = value
                    Toast.makeText(context, "Tick Mode Dist에 $value 값이 적용 되었습니다.", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "$value 은 정상 값이 아닙니다. EditText를 되돌립니다.", Toast.LENGTH_SHORT).show()
                    binding.tickDist.setText(JogState.jogModeDist.toString())
                }
                true
            } else {
                false
            }
        }

        // tickOri EditText에 대한 리스너 설정
        binding.tickOri.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val value = binding.tickOri.text.toString().toFloatOrNull()

                //다음 코드에서 Ori에 들어갈 값의 범위를 조절합니다.
                if (value != null && value in 0.0f..360.0f) {
                    JogState.jogModeOri = value
                    Toast.makeText(context, "Tick Mode Ori에 $value 값이 적용 되었습니다.", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "$value 은 정상 값이 아닙니다. EditText를 되돌립니다.", Toast.LENGTH_SHORT).show()
                    binding.tickOri.setText(JogState.jogModeOri.toString())
                }
                true
            } else {
                false
            }
        }


        // tickJoint EditText에 대한 리스너 설정
        binding.tickJoint.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val value = binding.tickJoint.text.toString().toFloatOrNull()

                //다음 코드에서 Joint에 들어갈 값의 범위를 조절합니다.
                if (value != null && value in -180.0f..180.0f) {
                    JogState.jogModeJoint = value
                    Toast.makeText(context, "Tick Mode Joint에 $value 값이 적용 되었습니다.", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "$value 은 정상 값이 아닙니다. EditText를 되돌립니다.", Toast.LENGTH_SHORT).show()
                    binding.tickJoint.setText(JogState.jogModeJoint.toString())
                }
                true
            } else {
                false
            }
        }



//        JogMode를 담당하는 부분을 Fragment 로 분리 한 상태입니다. 이후 조금 더 테스트를 진행하고 주석 부분 삭제 예정.

        //Object JogState 의 jogModeSelected 변수 값에 따라 조그 모드 (Smooth, Tick) 의 초기 상태를 표현합니다.
        when(JogState.jogModeSelected){
            JogState.JOG_SMOOTH_SELECTED->{
                makeSmoothBt.setBackgroundResource(R.drawable.color_red_frame)
                makeSmoothBt.isEnabled=false
            }

            JogState.JOG_TICK_SELECTED-> {
                makeTickBt.setBackgroundResource(R.drawable.color_red_frame)
                makeTickBt.isEnabled = false

                tickDist.isVisible = true
                tickJoint.isVisible = true
                tickOri.isVisible = true
                jogModeTv1.isVisible = true
                jogModeTv2.isVisible= true
                jogModeTv3.isVisible = true
            }
        }

        //Jog mode Smooth 버튼 리스너
        makeSmoothBt.setOnClickListener{
            //중복 클릭 방지를 위해 본인 버튼을 비활성화 시키고 붉은 배경으로 교체합니다.
            makeSmoothBt.isEnabled = false
            makeSmoothBt.setBackgroundResource(R.drawable.color_red_frame)

            //Tick 버튼은 활성화 시키고 배경을 기본 프레임으로 설정합니다.
            makeTickBt.isEnabled = true
            makeTickBt.setBackgroundResource(R.drawable.public_button)

            //Tick에 관련된 요소들이 보이지 않게 가립니다.
            tickDist.isInvisible = true
            tickJoint.isInvisible = true
            tickOri.isInvisible = true
            jogModeTv1.isInvisible = true
            jogModeTv2.isInvisible = true
            jogModeTv3.isInvisible = true

            JogState.jogModeSelected = JogState.JOG_SMOOTH_SELECTED
        }


        //Jog Mode TIck 버튼 리스너
        makeTickBt.setOnClickListener{
            //중복 클릭 방지를 위해 본인 버튼을 비활성화 시키고 붉은 배경으로 교체합니다.
            makeTickBt.isEnabled = false
            makeTickBt.setBackgroundResource(R.drawable.color_red_frame)

            //SMooth 버튼을 활성화 시키고 배경을 기본 프레임으로 설정합니다.
            makeSmoothBt.isEnabled = true
            makeSmoothBt.setBackgroundResource(R.drawable.public_button)

            //Tick에 관련된 요소들을 활성화 시킵니다.
            tickDist.isVisible = true
            tickJoint.isVisible = true
            tickOri.isVisible = true
            jogModeTv1.isVisible = true
            jogModeTv2.isVisible= true
            jogModeTv3.isVisible = true

            JogState.jogModeSelected = JogState.JOG_TICK_SELECTED
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JogModeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JogModeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}