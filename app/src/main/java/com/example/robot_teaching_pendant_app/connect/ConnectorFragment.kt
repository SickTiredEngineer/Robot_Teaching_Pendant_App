package com.example.robot_teaching_pendant_app.connect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.robot_teaching_pendant_app.databinding.ConnectorFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class ConnectorFragment : Fragment() {

    protected var cbinding: ConnectorFragmentBinding? = null
    protected val binding get() = cbinding!!
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
        // Inflate the layout for this fragment
        //Connect Helper Class 를 통하여 연결 동작을 실행합니다.
        //연결 UI 동작들은 Connect Helper Class에서 실행되며, 자세한 코드는 해당 Class를 참고 하십시오.
        cbinding = ConnectorFragmentBinding.inflate(inflater, container, false)
        val localbinding = cbinding ?: return null

        connectHelper = ConnectHelper(
            connectBt = localbinding.connectBt,
            disconnectBt = localbinding.disconnectBt,
            stateConnect = localbinding.stateConnect,
            stateConBox = localbinding.stateConBox,
            stateRobOperBox = localbinding.stateRobOperBox
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cbinding = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConnectInterface.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConnectorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}