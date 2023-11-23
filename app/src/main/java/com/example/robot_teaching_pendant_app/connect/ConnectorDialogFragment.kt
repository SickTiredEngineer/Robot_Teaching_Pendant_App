package com.example.robot_teaching_pendant_app.connect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.robot_teaching_pendant_app.databinding.ConnectorFragmentBinding
//ConnectorFragment를 Dialog 형식으로 보여주는 Fragment 입니다.
//ConnectorHelper Class를 이용합니다.


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectorDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectorDialogFragment : DialogFragment() {

    private var _binding: ConnectorFragmentBinding? = null
    private val cbinding get() = _binding!!
    private lateinit var connectHelper: ConnectHelper

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
        _binding = ConnectorFragmentBinding.inflate(inflater, container, false)

        //ConnectHelper 클래스의 생성자에 맞게, 위젯을 추가해줍니다.
        connectHelper = ConnectHelper(
            connectBt = cbinding.connectBt,
            disconnectBt = cbinding.disconnectBt,
            stateConnect = cbinding.stateConnect,
            stateConBox = cbinding.stateConBox,
            stateRobOperBox = cbinding.stateRobOperBox
        )

        //해당 Dialog 는 바깥 배경을 클릭하여 Cancel할 수 없습니다.
        return cbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
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
         * @return A new instance of fragment FragmentDialogConnector.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConnectorDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    
}