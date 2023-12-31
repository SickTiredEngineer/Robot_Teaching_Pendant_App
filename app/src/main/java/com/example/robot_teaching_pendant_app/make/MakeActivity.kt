package com.example.robot_teaching_pendant_app.make

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.robot_teaching_pendant_app.MainActivity
import com.example.robot_teaching_pendant_app.command_tree.CommandTreeViewModel
import com.example.robot_teaching_pendant_app.command_tree.CommandTreeFragment
import com.example.robot_teaching_pendant_app.connect.ConnectorDialogFragment
import com.example.robot_teaching_pendant_app.databinding.MakeActivityBinding
import com.example.robot_teaching_pendant_app.play.PlayActivity
import com.example.robot_teaching_pendant_app.setup.SetupActivity
import com.example.robot_teaching_pendant_app.system.PowerOffDialogFragment

class MakeActivity : AppCompatActivity(),CommandTreeFragment.treeEditorListener {

    /**
    MakeActivity는 RobotPrograme을 생성하는 작업화면입니다. 해당 Acitivity는 큰 틀이며, 실질적으로 제작에 필요한 기능들은 Fragment 형식으로 불러오게 됩니다.
     왼쪽 레이어에는 CommandTreeFragment 를 불러와서 명령어를 Tree 형식 (사실상 List)으로 불러와서 보여줍니다.
     오른쪽 레이어는 각종 기능들이 있는 MakeDefaultFragment 혹은 ExtFragment(확장 Fragment)를 불러옵니다.
     */


    private lateinit var binding: MakeActivityBinding

    /**
    MakeDefaultFragment에서 생성된 Icon Button을 누르면 ViewModel을 이용하여 CommandTree와 통신 및 UI 갱신을 진행합니다. ViewModel 인스턴스는 데이터 변경사항을 관찰하고 이에 따라 Ui 업데이트를 진행.
    여기선 CommandTreeViewModel인스턴스를 참조합니다.
    by viewModels() -> viewModel 인스턴스는 액티비티의 생명주기와 연결되고 필요에 따라 자동 생성됩니다.
     */

    val commandTreeViewModel: CommandTreeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Make(작업 환경) 화면을 바인딩 합니다.
        binding = MakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //좌측 상단에 위치한 화면 이동 메뉴 버튼
        val makeMenuBt = binding.makeMenuBt

        val makeTreeView = binding.makeTreeView

        val makePowerBt = binding.makePowerBt
        val treeExtBt = binding.treeExtBt

        /* TREE를 제외한 우측에 요소들을 표시해주는 View 와 기본적으로 보여지는 화면(Default) 설정이며, 확장 버튼 클릭 시 ext Activity로 교체합니다. */
        val sideViewer = binding.sideView

        val makeConnectBt = binding.makeConnectBt

        val treeDelBt = binding.treeDelBt
        val treeUpBt = binding.treeUpBt
        val treeDownBt = binding.treeDownBt



        /**
        ===================================================================================================================================
        아래의 리스너들은 CommandTree에 있는 명령어의 Editor들 입니다.
         해당 리스너에 들어가는 함수들은 , CommandTreeFragment 의 인터페이스에 구현되어 있습니다.
         */


        treeDelBt.setOnClickListener{
            doCommandDelect()
        }

        treeUpBt.setOnClickListener{
            doCommandUp()
        }

        treeDownBt.setOnClickListener{
            doCommandDown()
        }


        /**
        ===================================================================================================================================
        아래의 코드들은 MakeActivity 에서 사용할 Fragment 들을 불러옵니다.
         */


        //Ext, Default Fragment 를 변수에 초기화합니다.
        val fragmentExt = MakeExtFragment()
        val fragmentDefault = MakeDefaultFragment()


        //토글 형식의 트리 확장 버튼(EXT)이 꺼져있으면 MakeDefaultFragment를, 켜져있으면 MakeExtFragment를 불러옵니다.
        val initialFragment = if (treeExtBt.isChecked) fragmentExt else fragmentDefault

        supportFragmentManager.beginTransaction()
            .add(sideViewer.id, initialFragment)
            .commit()


        //자세한 내용은 Make 디렉토리의 MakeDefaultFragment를 참고하십시요.
        if (savedInstanceState == null) {
            val fragment = MakeDefaultFragment()
            supportFragmentManager.beginTransaction()
                .replace(sideViewer.id, fragment)
                .commit()


            //트리 프래그먼트를 삽입하는 코드입니다.
            val treeFragment = CommandTreeFragment()
            supportFragmentManager.beginTransaction()
                .replace(makeTreeView.id, treeFragment)
                .commit()
        }

        /**
        ===================================================================================================================================
         */



        //Ext tree(트리 확장) 토글 버튼 클릭 시 동작입니다.
        treeExtBt.setOnCheckedChangeListener { _, isChecked ->

            //토클버튼이 true 일땐 ExtFragment , False 일 땐 DefaultFragment 로 값을 주고 프래그먼트를 불러옵니다.
            val fragment = if(isChecked) {
                MakeExtFragment()
                } else {
                    MakeDefaultFragment()
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(sideViewer.id, fragment)
                        .commit()
                }



        //좌측 상단에 위치한 Menu 버튼 동작, Dialog 형식으로 해당 화면으로 이동할 수 있는 기능
        makeMenuBt.setOnClickListener{
            val menuDialog = AlertDialog.Builder(this)
            val menuArray = arrayOf("메인 화면", "실행", "환경 설정")


            //바깥 화면을 클릭 하여 창을 닫을 수 있습니다.
            menuDialog.setCancelable(true)
            menuDialog.setItems(menuArray) { _, p1 ->
                when (menuArray[p1]) {
                    "메인 화면" -> {
                        val nextIntent = Intent(this@MakeActivity, MainActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                    "실행" -> {
                        val nextIntent = Intent(this@MakeActivity, PlayActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                    "환경 설정" -> {
                        val nextIntent = Intent(this@MakeActivity, SetupActivity::class.java)
                        startActivity(nextIntent)
                        finish()
                    }
                }
            }
            menuDialog.show()
        }



        //우측 하단에 위치한 연결 버튼을 누를 시, ConnectorDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 Connect 디렉토리의 ConnectorDialogFragment를 참고하십시요.
        makeConnectBt.setOnClickListener{
            val dialogFragment = ConnectorDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }


        //우측 하단에 위치한 전원 버튼을 누를 시, PowerOffDialogFragment 를 Dialog 형식으로 출력합니다.
        //자세한 내용은 System 디렉토리의 PowerOffDialogFragment를 참고하십시요.
        makePowerBt.setOnClickListener{
            val dialogFragment = PowerOffDialogFragment()
            dialogFragment.show(supportFragmentManager,null)
        }
    }



    /**
    CommandTreeFragment와 통신하기 위한 Interface 구현 부분입니다.
     명령어를 삭제하거나 위 아래로 이동하는 등의 동작을 수행합니다.
     */
    override fun doCommandDelect() {
        val treeEditor = supportFragmentManager.findFragmentById(binding.makeTreeView.id) as CommandTreeFragment
        runOnUiThread {
            treeEditor.deleteSelectedCommand()
        }
    }

    override fun doCommandDown() {
        val treeEditor = supportFragmentManager.findFragmentById(binding.makeTreeView.id) as CommandTreeFragment
        runOnUiThread {
            treeEditor.moveCommandDown()
        }
    }

    override fun doCommandUp() {
        val treeEditor = supportFragmentManager.findFragmentById(binding.makeTreeView.id) as CommandTreeFragment
        runOnUiThread {
            treeEditor.moveCommandUp()
        }
    }


}