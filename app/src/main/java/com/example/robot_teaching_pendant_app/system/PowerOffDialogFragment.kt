package com.example.robot_teaching_pendant_app.system

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.PowerOffDialogFragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PowerOffDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PowerOffDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.style_power_dialog_size)

//        arguments?.let {
//
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
//        }
    }

    //화면 비율을 맞추는 코드입니다. (Wrap_Content)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }


    private lateinit var binding: PowerOffDialogFragmentBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = PowerOffDialogFragmentBinding.inflate(LayoutInflater.from(context))

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

        //해당 Dialog 는 바깥 배경을 클릭하여 Cancel할 수 없습니다.
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
            PowerOffDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}