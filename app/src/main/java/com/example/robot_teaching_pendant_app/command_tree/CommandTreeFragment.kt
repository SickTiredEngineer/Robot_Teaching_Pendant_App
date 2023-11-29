package com.example.robot_teaching_pendant_app.command_tree

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.command.RobotCommand
import com.example.robot_teaching_pendant_app.databinding.CommandTreeFragmentBinding
import com.example.robot_teaching_pendant_app.databinding.JogFragmentBinding
import com.example.robot_teaching_pendant_app.databinding.MakeDefaultFragmentBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommandTreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommandTreeFragment : Fragment() {

    private var _binding: CommandTreeFragmentBinding? = null
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
        _binding = CommandTreeFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scrollView = binding.commandTreeScrollView

        //Fragment 가 불러와지면, 현재 CommandTree 의 리스트에 있는 명령어들을 TextView 형태로 출력합니다.
        activity?.runOnUiThread {
            refreshCommandTree()
        }


        //ViewModel -> 이후 필요한 부분마다 추가 적용하며 , 계속 변경할 예정.
        //Tree 기초 틀만 구현 한 상태이고, 해당 메서드만 호출되는 상태입니다.
        (activity as? MakeActivity)?.commandTreeViewModel?.updateEvent?.observe(viewLifecycleOwner) {
            refreshCommandTree()
        }
    }


    private fun createTextViewForCommand(index: Int, command: RobotCommand): TextView {
        val textView = TextView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )

            typeface = Typeface.create("sans-serif", Typeface.BOLD)

            // 해당 인스턴스의 순서와 내용을 표시합니다.
            text = "[$index] [${command.type}] ${command.toString()}" // 순서와 내용을 함께 표시

            // 추가적인 스타일링을 할 수 있습니다.
            background = context.getDrawable(R.drawable.main_frame)
        }

        return textView
    }


    fun refreshCommandTree() {
        binding.commandTreeScrollView.removeAllViews()

        CommandTree.commandList.forEachIndexed { index, command ->
            val textView = createTextViewForCommand(index, command)
            binding.commandTreeScrollView.addView(textView)
        }
    }

//    fun addCommandToTree(command: RobotCommand) {
//        val index = CommandTree.commandList.size
//        val textView = createTextViewForCommand(index, command)
//        binding.commandTreeScrollView.addView(textView)
//
//        // CommandTree에 해당 명령어 추가
//        CommandTree.commandList.add(command)
//    }



//    fun refreshCommandTree(){
//        binding.commandTreeScrollView.removeAllViews()
//
//        CommandTree.commandList.forEachIndexed() { index, command ->
//            val textView = TextView(context).apply {
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//
//                text = "[$index] ${command.toString()}"
//
//                // 추가적인 스타일링을 할 수 있습니다.
//                background = context.getDrawable(R.drawable.main_frame)
//            }
//
//
//            // ScrollView의 자식 뷰인 LinearLayout에 TextView를 추가합니다.
//            // 바인딩을 통해 scrollView 내에 있는 LinearLayout에 접근한다고 가정합니다.
//            binding.commandTreeScrollView.addView(textView)
//        }
//    }



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
         * @return A new instance of fragment CommandTreeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommandTreeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}