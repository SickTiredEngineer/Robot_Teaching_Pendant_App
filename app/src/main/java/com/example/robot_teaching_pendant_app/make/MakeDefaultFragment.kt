package com.example.robot_teaching_pendant_app.make

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.MakeDefaultFragmentBinding
import com.example.robot_teaching_pendant_app.system.JogState
import com.example.robot_teaching_pendant_app.system.RobotPosition

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MakeDefaultFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val defBinding = MakeDefaultFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment


        //우측 상단 기능 카테고리 버튼들
        val defAllBt = defBinding.defAllBt
        val defMoveBt = defBinding.defMoveBt
        val defFuncBt = defBinding.defFuncBt
        val defOtherBt = defBinding.defOtherBt
        val searchBox = defBinding.searchBox


        //기능 버튼 아이콘들이 출력 될 레이아웃
        val defIconView = defBinding.defIconView

        //Jog Controller를 삽입 할 보여 줄 View 입니다.
        val jogViewer = defBinding.jogControllerView


        //defaultFragment 하위에 있는 긴급 정지 버튼과 빠른Home 기능 버튼입니다.
        val makeEstopBt = defBinding.makeEstopBt
        val makeQhomeBt = defBinding.makeQhomeBt


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

        val jogButtonList = listOf(jogGlobalBt, jogLocalBt, jogUserBt, jogJointBt)

        //Jogstate Object에 있는 jogSelected 값에 따라서 버튼의 Disable 상태를 결정합니다.
        when (JogState.jogSelected) {
            JogState.JOG_GLOBAL_SELECTED -> {
                jogGlobalBt.setBackgroundResource(R.drawable.color_gray_frame)
                jogGlobalBt.isEnabled = false
            }

            JogState.JOG_LOCAL_SELECTED -> {
                jogLocalBt.setBackgroundResource(R.drawable.color_gray_frame)
                jogLocalBt.isEnabled = false
            }

            JogState.JOG_USER_SELECTED -> {
                jogUserBt.setBackgroundResource(R.drawable.color_gray_frame)
                jogUserBt.isEnabled = false
            }

            JogState.JOG_JOINT_SELECTED -> {
                jogJointBt.setBackgroundResource(R.drawable.color_gray_frame)
                jogJointBt.isEnabled = false
            }
        }


//        Jog Fragment 를 childFragment 형태로 불러옵니다.
        if (savedInstanceState == null) { // <- saveInstanceState 가 Null 이면 프래그먼트가 처음 생성됨을 의미.

            //처움 생성되는 프래그먼트면 새로운 JogFragment 인스턴스를 생성합니다.
            val fragment = JogFragment()
            childFragmentManager.beginTransaction()
                .replace(jogViewer.id, fragment)

                //트랜잭션을 커밋하여 위에서 정의한 프래그먼트 변경사향들을 적용
                .commit()
        }



        //Quick Home 버튼의 클릭 리스너입니다. 좌표계 값들을 영점으로 만들고 Fragment 를 다시 불러옵니다.
        makeQhomeBt.setOnClickListener {

            RobotPosition.joint1 = 0f
            RobotPosition.joint2 = 0f
            RobotPosition.joint3 = 0f
            RobotPosition.joint4 = 0f

            RobotPosition.x = 0f
            RobotPosition.y = 0f
            RobotPosition.z = 0f
            RobotPosition.Rx = 0f
            RobotPosition.Ry = 0f
            RobotPosition.Rz = 0f


            val fragment = JogFragment()
            (it.context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(jogViewer.id, fragment)
                .commit()
        }


        //조그를 선택하는 버튼들에 대한 UI 로직입니다. 본인은 비활성화 시키고, 나머지 3가지 버튼은 활성화 시켜줍니다. (중복 클릭 방지)
        fun Button.assignJogState(value: Int, buttonList: List<Button>, fragmentContainer: Int) {
            //조그 모드 버튼들에 대한 클릭 리스너
            this.setOnClickListener {

                //Object Jogstate 의 jogSelected(조그 선택상태) 변수를 value인자값으로 변경합니다.
                JogState.jogSelected = value

                for (otherButton in buttonList) {
                    //버튼 리스트에 있는 것이 자기 자신이면 자기 자신을 비활성화 하고 회색 배경으로 변경하는 루프문입니다.
                    if (otherButton == this) {
                        otherButton.isEnabled = false
                        otherButton.setBackgroundResource(R.drawable.color_gray_frame) // 회색 배경 리소스

                        //눌러진 버튼의 이름을 출력하는 토스트문
                        val buttonText = otherButton.text
                        Toast.makeText(it.context, "$buttonText 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show()


                    //눌러진 버튼이 자기 자신이 아니면 활성화 되며 배경을 기본 버튼 프레임으로 변경합니다.
                    } else {
                        otherButton.isEnabled = true
                        otherButton.setBackgroundResource(R.drawable.public_button) // 기본 배경 리소스
                    }
                }

                // 버튼을 클릭하면 프래그먼트를 새로고침합니다.
                val fragment = JogFragment()
                (it.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(fragmentContainer, fragment)
                    .commit()
            }
        }

        //위의 함수를 이용하여 조그를 선택하는 4가지 버튼을 , 위의 함수를 활용하여 그에 맞는 모드와 UI 로직을 가지게 합니다.
        jogGlobalBt.assignJogState(JogState.JOG_GLOBAL_SELECTED, jogButtonList, jogViewer.id)
        jogLocalBt.assignJogState(JogState.JOG_LOCAL_SELECTED, jogButtonList, jogViewer.id)
        jogUserBt.assignJogState(JogState.JOG_USER_SELECTED, jogButtonList, jogViewer.id)
        jogJointBt.assignJogState(JogState.JOG_JOINT_SELECTED, jogButtonList, jogViewer.id)


/*        카테고리를 클릭하면 해당 카테고리에 포함 된 아이콘 버튼을 -> defIconView 라는 스크롤 바 뷰 안에 포함되어 있는 gridLayout에 출력하는
        함수입니다. 아이콘 버튼은 make 디렉토리의 Icon Data Class 를 참조하십시오.*/

        fun displayIcons(icons: List<Icon>) {
            val gridLayout = defBinding.gridLayout

            //카테고리 버튼이 클릭되면 먼저 레이아웃에 있는 뷰를 전부 제거합니다.
            gridLayout.removeAllViews()

            //버튼 추가를 위한 루프 문
            for (iconData in icons) {
                val iconButton = ImageButton(context)
                iconButton.setImageResource(iconData.imageRes)

                //FIT_CENTER는 이미지를 뷰의 중앙에 위치시키면서 뷰에 맞게 이미지를 조절합니다. 이미지의 가로세로 비율은 유지됩니다.
                //CENTER_INSIDE는 이미지가 뷰의 경계 내에 완전히 들어오도록 이미지 크기를 조절합니다. 이미지의 가로세로 비율 역시 유지됩니다.
                iconButton.scaleType = ImageView.ScaleType.CENTER_INSIDE
                iconButton.background = null

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    iconButton.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.icon_frame)
                }

                val params = GridLayout.LayoutParams()
                params.width = 150
                params.height = 150
                params.setMargins(10, 10, 10, 10)  // 마진 설정: 좌, 상, 우, 하

// 아이콘 버튼의 레이아웃 파라미터에 위에서 설정한 params를 적용합니다.
                iconButton.layoutParams = params
                iconButton.background = null

                //아이콘 버튼의 리스너입니다.
                iconButton.setOnClickListener {
                    Toast.makeText(context, "${iconData.title} 버튼이 눌러졌습니다.", Toast.LENGTH_SHORT).show()
                    iconData.action.invoke() }

                //Gridlayout 에 iconButton 을 추가합니다.
                gridLayout.addView(iconButton)
            }
        }


        // 아래는 우측 상단의 기능 카테고리 버튼들(all, move, func 등)을 클릭 시 리스너 동작들입니다.
        defAllBt.setOnClickListener{
            displayIcons(allIcons)
        }

        defMoveBt.setOnClickListener{
            displayIcons(moveIcons)
        }



        fun filterAndDisplayIcons(query: String) {
            // query를 기반으로 icons 리스트를 필터링
            val filteredIcons = allIcons.filter { icon ->
                icon.title.contains(query, ignoreCase = true)  // 대소문자를 무시하고 포함 여부 확인
            }

            displayIcons(filteredIcons)  // 필터링된 아이콘 리스트를 출력
        }


        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // 입력 전
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val currentText = s.toString()  // s를 String으로 변환
                if (currentText.contains("\n") || currentText.contains("\r")) {
                    // CRLF나 엔터키가 포함된 경우, 해당 문자를 제거
                    val newText = currentText.replace("\n", "").replace("\r", "")
                    searchBox.setText(newText)

                    // 커서를 텍스트의 끝으로 이동
                    searchBox.setSelection(newText.length)

                    // 필요하다면 사용자에게 경고 메시지 표시
                    Toast.makeText(context, "엔터키 입력은 허용되지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    filterAndDisplayIcons(currentText)
                }

            }

            override fun afterTextChanged(s: Editable) {
                // 입력 후
            }
        })




        //Activity 에서도 접근 하려면 필요한 코드, 임시로 남겨 둠, 해당 파일은 Fragment 라 사용하지 않아서 주석으로만 남겨둠.
//        if (savedInstanceState == null) {
//            val fragment = JogFragment()
//            activity?.supportFragmentManager?.beginTransaction()
//                .replace(jogViewer.id, fragment)
//                .commit()
//        }


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
            MakeDefaultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}

