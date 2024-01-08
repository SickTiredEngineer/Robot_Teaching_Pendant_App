package com.example.robot_teaching_pendant_app.make

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.PositionViewerFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState
import com.example.robot_teaching_pendant_app.system.RobotPosition

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PositionViewerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class PositionViewerFragment : Fragment() {

    /**
    해당 Fragment는 로봇의 위치(ROBOT POSITION)을 TextView 형식으로 사용자에게 보여줍니다.
     View Model 을 적용하려고 하였으나 현재 해결하지 못하여, setTextView() 함수를 이용하여 갱신중입니다.
     이후 자료를 더 찾아서 View Model을 적용하고, 출력을 최적화시킬 예정입니다.
     */

    //MakeDefaultFragment 에서 TextView 최신화를 위해 현재 프래그먼트와 통신하기 위한 인터페이스로 부모 Activity가 중계합니다.
    interface FragmentCommunicationInterface{
        fun refreshTextView()
    }


    private var _binding: PositionViewerFragmentBinding? = null
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


        _binding = PositionViewerFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProviders.of(requireActivity()).get(RobotPositionViewModel::class.java)

        //Fragment가 불러와 졌을 때, 초기 상태는 아래의 setTextView()함수를 이용하여 설정합니다.
        setTextView()

    }



    /**
    선택된 좌표계에 따라 TextView 전체를 최신화 시키는 함수입니다. -> ViewModel 적용이 가능하다면 사용하지 않을수도 있습니다.
    TextView에 넣을 변수 값을 Object RobotPosition 에서 불러와서 소숫점 2째자리 까지 출력하도록 설정합니다.
     */

    fun setTextView(){
        when(JogState.jogSelected){
            JogState.JOG_GLOBAL_SELECTED->{
                binding.xTv.setText(" x: %.2f".format(RobotPosition.global_x))
                binding.yTv.setText(" y: %.2f".format(RobotPosition.global_y))
                binding.zTv.setText(" z: %.2f".format(RobotPosition.global_z))
                binding.rxTv.setText(" Rx: %.2f".format(RobotPosition.global_Rx))
                binding.ryTv.setText(" Ry: %.2f".format(RobotPosition.global_Ry))
                binding.rzTv.setText(" Rz: %.2f".format(RobotPosition.global_Rz))
            }

            JogState.JOG_LOCAL_SELECTED->{
                binding.xTv.setText(" x: %.2f".format(RobotPosition.local_x))
                binding.yTv.setText(" y: %.2f".format(RobotPosition.local_y))
                binding.zTv.setText(" z: %.2f".format(RobotPosition.local_z))
                binding.rxTv.setText(" Rx: %.2f".format(RobotPosition.local_Rx))
                binding.ryTv.setText(" Ry: %.2f".format(RobotPosition.local_Ry))
                binding.rzTv.setText(" Rz: %.2f".format(RobotPosition.local_Rz))
            }

            JogState.JOG_USER_SELECTED->{
                binding.xTv.setText(" x: %.2f".format(RobotPosition.user_x))
                binding.yTv.setText(" y: %.2f".format(RobotPosition.user_y))
                binding.zTv.setText(" z: %.2f".format(RobotPosition.user_z))
                binding.rxTv.setText(" Rx: %.2f".format(RobotPosition.user_Rx))
                binding.ryTv.setText(" Ry: %.2f".format(RobotPosition.user_Ry))
                binding.rzTv.setText(" Rz: %.2f".format(RobotPosition.user_Rz))
            }
        }


        binding.joint1Tv.setText(" joint1: %.2f".format(RobotPosition.joint1))
        binding.joint2Tv.setText(" joint2: %.2f".format(RobotPosition.joint2))
        binding.joint3Tv.setText(" joint3: %.2f".format(RobotPosition.joint3))
        binding.joint4Tv.setText(" joint4: %.2f".format(RobotPosition.joint4))
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
         * @return A new instance of fragment PositionViewerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PositionViewerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}