package com.example.robot_teaching_pendant_app.command_tree

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.command.CommandType
import com.example.robot_teaching_pendant_app.command.RobotCommand
import com.example.robot_teaching_pendant_app.databinding.CommandTreeFragmentBinding
import com.example.robot_teaching_pendant_app.make.MakeActivity
import java.util.Collections

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

    //MakeActivity에서 트리를 수정하는 버튼들(삭제, 위, 아래 등..)을 위한 인터페이스 입니다.
    interface treeEditorListener{
        fun doCommandDelect()
        fun doCommandUp()
        fun doCommandDown()
    }

    //이전에 선택된 TextView를 표시합니다.
    private var previouslySelectedTextView: TextView? = null

    //현재 선택된 TextView의 Index를 표시합니다.
    private var selectedIndex: Int? = null

    //현재 선택된 TextView를 표시합니다.
    private var selectedTextView: TextView? = null

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
    ): View {
        _binding = CommandTreeFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Fragment가 불러와지면, 현재 CommandTree의 전역 리스트에 있는 명령어들을 순서대로 TextView 형태로 표시해줍니다.
        activity?.runOnUiThread {
            refreshCommandTree()
        }

        //ViewModel -> 이후 필요한 부분마다 추가 적용하며 , 계속 변경할 예정.
        //Tree 기초 틀만 구현 한 상태이고, 해당 메서드만 호출되는 상태입니다.
        (activity as? MakeActivity)?.commandTreeViewModel?.updateTextViewEvent?.observe(viewLifecycleOwner) {
            refreshCommandTree()
        }
    }



    /* -------------------------------------------------------------------------*/
    /**
     * CommandTree에 있는 각 명령어에 대해 TextView를 생성하고, TextView들에 대한 세밀한 설정을 하는 함수입니다.
     * @param index 명령어의 순서를 나타내는 인덱스입니다.
     * @param command 현재 명령어의 인스턴스입니다.
     * @return 설정이 완료된 TextView를 반환합니다.
     */

    private fun createTextViewForCommand(index: Int, command: RobotCommand): TextView {
        val textView = TextView(context)

        //기존 TextView 생성 함수의 기능들을 4단계로 분리한 상태입니다. 해당 함수들은 현재 함수의 바로 아래에 순서대로 나열되어 있습니다.

        //TextView의 기본적인 설정을 담당하는 함수입니다.
        setupTextView(textView, index, command)

        //CommandType 에 따라 TextView의 제일 좌측에 들어갈 Icon을 정하는 함수입니다.
        setCommandIcon(textView, command.type)

        //TextView의 클릭 리스너입니다.
        setClickListener(textView, index, command)

        //TextView의 롱클릭 리스너입니다.
        setLongClickListener(textView, index, command)
        
        return textView
    }


    /* -------------------------------------------------------------------------*/

    //해당 주석을 기준으로 다음에 나올 4의개 함수들은 위의 createTextView 에서 textView를 생성할 때 사용할 함수들입니다.

    //1. TextView의 폰트, 사이즈 등을 설정하는 함수입니다.
    private fun setupTextView(textView: TextView, index: Int, command: RobotCommand) {
        textView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            typeface = Typeface.create("sans-serif", Typeface.BOLD)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            text = "[$index] [${command.type}] "
            background = AppCompatResources.getDrawable(context, R.drawable.main_frame)
        }
    }


    //2. 명령어의 Type에 따라 TextView 좌측에 생성될 아이콘을 정합니다.
    private fun setCommandIcon(textView: TextView, commandType: CommandType) {

        //CommandType에 따라 설정에 사용될 아이콘을 지정합니다.

        val iconResId = when(commandType) {
            CommandType.MOVE_J -> R.drawable.bt_movej_icon
            CommandType.MOVE_JB -> R.drawable.bt_move_jb_icon

            CommandType.MOVE_L -> R.drawable.bt_movel_icon
            CommandType.MOVE_LB-> R.drawable.bt_move_lb_icon

            CommandType.CIRCLE -> R.drawable.bt_circlemove_icon
            else->0
        }


        //iconResId 가 Else 가 아닐 경우에만 아래의 로직을 실행합니다.
        if (iconResId != 0) {
            //Context가 Null이 아닐 경우에만 App.CompatResource.getDrawable을 호출합니다.
            val drawable = context?.let { AppCompatResources.getDrawable(it, iconResId) }

            //현재 가로, 세로 사이즈를 동일하게 사용하기 때문에 하나의 값만 필요하나, 필요 시 변수를 더 선언하여 값을 사용할 수 있습니다.
            val iconSize = 50

            drawable?.setBounds(0, 0, iconSize, iconSize)

            textView.setCompoundDrawables(drawable, null, null, null)

            //TextView의 글자와 아이콘의 간격을 설정합니다.
            textView.compoundDrawablePadding = 15
        }
    }


    //3. TextView의 클릭 리스너를 설정합니다.
    private fun setClickListener(textView: TextView, index: Int, command:RobotCommand) {
        textView.setOnClickListener {
            // context가 null이면 리스너를 종료합니다.
            val context = context ?: return@setOnClickListener


            // 현재 클릭된 TextView가 이전에 선택된 TextView와 다른 경우
            if (previouslySelectedTextView != textView) {

                // 이전에 선택된 TextView의 배경을 원래대로 되돌립니다.
                previouslySelectedTextView?.background = AppCompatResources.getDrawable(context, R.drawable.main_frame)

                // 현재 TextView의 배경을 강조색으로 설정합니다.
                textView.background = AppCompatResources.getDrawable(context, R.drawable.color_green_frame)

                // 새로운 TextView를 현재 선택된 TextView로 설정합니다.
                previouslySelectedTextView = textView

                // 선택된 명령의 인덱스를 업데이트합니다.
                selectedIndex = index


            } else {
                // 이미 선택된 TextView를 다시 클릭한 경우, 선택을 해제합니다.
                textView.background = AppCompatResources.getDrawable(context, R.drawable.main_frame)
                previouslySelectedTextView = null
                selectedIndex = null
            }
        }
    }


    //4. TextView의 롱클릭 리스너를 설정합니다. 롱클릭 리스너는 이후 해당 명령어를 수정할 수 있는 Fragment를 Dialog 형식으로 출력하는 역할을 합니다.
    private fun setLongClickListener(textView: TextView, index: Int, command:RobotCommand) {
        textView.setOnLongClickListener {
            // context가 null이면 리스너를 종료합니다.
            val context = context ?: return@setOnLongClickListener true

            // 롱 클릭 확인을 위한 토스트 메시지를 표시합니다.
            Toast.makeText(context, "${index} 번째 ${command.type} 버튼이 눌러졌습니다. 해당 메시지는 확인용 토스트 입니다", Toast.LENGTH_SHORT).show()

            // 현재 클릭된 TextView가 이전에 선택된 TextView와 다른 경우
            if (previouslySelectedTextView != textView) {

                // 이전에 선택된 TextView의 배경을 원래대로 되돌립니다.
                previouslySelectedTextView?.background = AppCompatResources.getDrawable(context, R.drawable.main_frame)

                // 현재 TextView의 배경을 강조색으로 설정합니다.
                textView.background = AppCompatResources.getDrawable(context, R.drawable.color_green_frame)

                // 새로운 TextView를 현재 선택된 TextView로 설정합니다.
                previouslySelectedTextView = textView

                // 선택된 명령의 인덱스를 업데이트합니다.
                selectedIndex = index
            }

            // 롱 클릭 이벤트가 처리되었음을 나타내기 위해 true를 반환합니다.
            true
        }
    }


    /* -------------------------------------------------------------------------*/

    /**
     * CommandTree의 명령어 리스트를 UI에 반영하여 갱신하는 함수입니다.
     * 이 함수는 명령어 리스트에 변경이 있을 때 호출됩니다.
     */
    fun refreshCommandTree() {
        // ScrollView 내의 모든 뷰를 제거하여 새로 갱신할 준비를 합니다.
        binding.commandTreeScrollView.removeAllViews()

        // CommandTree의 명령어 리스트를 순회하면서 각 명령어에 대한 TextView를 생성하고 ScrollView에 추가합니다.
        CommandTree.commandList.forEachIndexed { index, command ->
            val textView = createTextViewForCommand(index, command)
            binding.commandTreeScrollView.addView(textView)


            if (index == selectedIndex) {
                textView.background = context?.let { ctx ->
                    AppCompatResources.getDrawable(ctx, R.drawable.color_green_frame)
                }
                selectedTextView = textView  // 현재 선택된 TextView를 업데이트합니다.
            }
        }
    }



    /**
     * 현재 선택된 명령을 리스트에서 한 칸 위로 이동시키는 함수입니다.
     * `selectedIndex`는 현재 선택된 `RobotCommand`의 리스트 내 인덱스를 가리킵니다.
     */
    fun moveCommandUp() {
        // 'let' 스코프 함수를 사용하여 'selectedIndex'가 null이 아닐 경우에만 코드 블록을 실행합니다.
        selectedIndex?.let { currentIndex ->

            // 현재 인덱스가 0보다 큰 경우에만 위로 이동이 가능합니다.
            // 이는 첫 번째 요소가 이미 가장 위에 있으므로 더 이상 위로 갈 수 없기 때문입니다.
            if (currentIndex > 0) {
                // Collections.swap 메서드를 사용하여 현재 선택된 명령과 바로 위에 있는 명령의 위치를 교환합니다.
                // currentIndex는 선택된 명령의 현재 위치이며, currentIndex - 1은 바로 위의 위치입니다.
                Collections.swap(CommandTree.commandList, currentIndex, currentIndex - 1)

                // 선택된 명령이 위로 이동했으므로, selectedIndex를 현재 인덱스에서 1 감소시켜 업데이트합니다.
                // 이는 다음에 동일한 명령을 조작할 때 올바른 위치를 참조하도록 합니다.
                selectedIndex = currentIndex - 1

                // 명령의 위치가 변경되었으므로, 이 변경을 사용자에게 반영하기 위해 UI를 새로고침합니다.
                // refreshCommandTree 함수는 전체 명령 리스트를 다시 그려서 ScrollView에 추가합니다.
                refreshCommandTree()
            }
        }
    }


    /**
     * 현재 선택된 명령을 아래로 이동시키는 함수입니다.
     * `selectedIndex`는 현재 선택된 `RobotCommand`의 리스트 내 인덱스입니다.
     */
    fun moveCommandDown() {
        // 'let' 스코프 함수를 사용하여 'selectedIndex'가 null이 아닐 경우에만 코드 블록을 실행합니다.
        selectedIndex?.let { currentIndex ->

            // 리스트의 마지막 인덱스보다 작은지 확인하여, 마지막 요소가 아닐 경우에만 아래로 이동이 가능하도록 합니다.
            if (currentIndex < CommandTree.commandList.size - 1) {

                // Collections.swap 메서드를 호출하여 현재 인덱스의 명령과 바로 아래 인덱스의 명령의 위치를 교환합니다.
                // 이를 통해 사용자 인터페이스에서 선택한 명령을 리스트에서 아래로 이동시킵니다.
                Collections.swap(CommandTree.commandList, currentIndex, currentIndex + 1)

                // 명령어 순서를 아래로 내린 후에는, 'selectedIndex'를 업데이트하여
                // 이동된 위치 (현재 위치 + 1)를 반영합니다.
                selectedIndex = currentIndex + 1

                // 리스트의 순서가 변경되었으므로 UI를 새로고침하여 변경 사항을 사용자에게 보여줍니다.
                // 이 함수는 커맨드 리스트를 다시 그리고, 각 명령에 대한 TextView를 다시 생성합니다.
                refreshCommandTree()
            }
        }
    }

    fun deleteSelectedCommand() {
        // 'let' 스코프 함수를 사용하여 'selectedIndex'가 null이 아닐 경우에만 코드 블록을 실행합니다.
        selectedIndex?.let { currentIndex ->

            // CommandTree.commandList에서 현재 선택된 명령을 제거합니다.
            CommandTree.commandList.removeAt(currentIndex)

            // 선택된 명령을 제거한 후에는 selectedIndex를 null로 초기화합니다.
            selectedIndex = null
            previouslySelectedTextView?.let {
                // 이전에 선택된 TextView의 배경색을 기본 프레임으로 되돌립니다.
                context?.let { ctx ->
                    it.background = AppCompatResources.getDrawable(ctx, R.drawable.main_frame)
                }

                // 이전 선택된 TextView 참조도 제거합니다.
                previouslySelectedTextView = null
            }

            // 명령 리스트에서 명령을 제거한 후 UI를 새로고침하여 변경 사항을 반영합니다.
            refreshCommandTree()
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