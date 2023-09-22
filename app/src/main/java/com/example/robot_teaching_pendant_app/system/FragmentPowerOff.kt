package com.example.robot_teaching_pendant_app.system

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.FragmentPowerOffBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPowerOff.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPowerOff : DialogFragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null


    //Dialog 의 사이즈를 원하는 만큼 DP로 설정합니다.
    override fun onStart() {
        super.onStart()

        val widthInDp = 1000
        val heightInDp = 600

        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp.toFloat(), resources.displayMetrics).toInt()
        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp.toFloat(), resources.displayMetrics).toInt()

        val params = dialog?.window?.attributes
        params?.width = width
        params?.height = height
        dialog?.window?.attributes = params
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.style_power_dialog_size)

//        arguments?.let {
//
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
//        }
    }

    private lateinit var binding: FragmentPowerOffBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = FragmentPowerOffBinding.inflate(LayoutInflater.from(context))

        val cancelBt = binding.cancelBt
        val shutdownBt = binding.shutdownBt

        cancelBt.setOnClickListener{
            Toast.makeText(requireContext(), "Cancel Button 클릭", Toast.LENGTH_SHORT ).show()
            dismiss()
        }

        shutdownBt.setOnClickListener{
            Toast.makeText(requireContext(), "Shutdown Button 클릭", Toast.LENGTH_SHORT ).show()
            dismiss()
        }

        val dialog = Dialog(requireActivity())
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        return dialog
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentPowerOff.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPowerOff().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}