package com.example.robot_teaching_pendant_app.setup

import android.os.Bundle
import android.os.Environment
import android.text.InputFilter
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.robot_teaching_pendant_app.R
import com.example.robot_teaching_pendant_app.databinding.SetupCoordFragmentBinding
import com.example.robot_teaching_pendant_app.databinding.SetupSystemFragmentBinding
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetupSystemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetupSystemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: SetupSystemFragmentBinding? = null
    private val binding get() = _binding!!


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
        _binding = SetupSystemFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //IP를 입력받는 EditText 입니다.
        val getIpEditText = binding.ipEditText

        //첫 번째 PORT를 입력받는 EditText 입니다.
        val getPortEditText = binding.portEditText

        //두 번째 PORT를 입력받는 EditText 입니다.
        val getSecondPortEditText = binding.secondPortEditText


        //클릭 시, 저장을 수행하는 버튼입니다.
        val saveBt = binding.systemSaveBt


        //IP입력창에 숫자와 . 만 입력되게 제한을 겁니다.
        getIpEditText.filters = arrayOf(IpAddressInputFilter())


        //저장 버튼의 클릭 리스너로 저장 로직을 실행합니다.
        saveBt.setOnClickListener{

            //모든 설정 값이 정상인 경우, True 상태를 유지하고, 문제가 있을 경우 False가 되며 설정이 저장되지 않습니다.
            var allValid = true


            /**
            ===================================================================================================================================
            설정 값들에 대한 유효성을 검사합니다.
            */


            //IP와 PORT1, PORT2를 YAML 파일 형식으로 저장하기 위해 EditText를 통해서 가져옵니다.
            val ip = getIpEditText.text.toString()
            val portStr = getPortEditText.text.toString()
            val secondPortStr = getSecondPortEditText.text.toString()


            //ValidateIpAddress 함수를 통해 EditText에서 불러온 값이 IP형식과 맞는지 검사합니다.
            //검사 후 문제가 없을 시, 토스트 메시지를 출력합니다.
            if (validateIpAddress(ip)) {
                Toast.makeText(context, "IP주소의 형식이 올바릅니다.", Toast.LENGTH_SHORT).show()

            //IP형식이 올바르지 않으면 설정을 저장하지 않습니다.
            } else {
                Toast.makeText(context, "Port1: 정상적인 IP주소 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                allValid = false
            }

            //ValidatePort 함수를 통해 EditText에서 불러온 값이 Port형식을 갖추고 있는지 검사합니다.
            //검사 후 문제가 없을 시, 토스트 메시지를 출력합니다.
            if (validatePort(portStr)) {
                Toast.makeText(context, "첫 번째 포트값의 형식이 올바릅니다.", Toast.LENGTH_SHORT).show()

            //PORT형식이 올바르지 않으면 설정을 저장하지 않습니다.
            } else {
                Toast.makeText(context, "Port1: 정상적인 포트 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                allValid = false
            }

            //ValidatePort 함수를 통해 EditText에서 불러온 값이 Port형식을 갖추고 있는지 검사합니다.
            //검사 후 문제가 없을 시, 토스트 메시지를 출력합니다.
            if (validatePort(secondPortStr)) {
                Toast.makeText(context, "두 번째 포트값의 형식이 올바릅니다.", Toast.LENGTH_SHORT).show()

            //PORT형식이 올바르지 않으면 설정을 저장하지 않습니다.
            } else {
                Toast.makeText(context, "Port2: 정상적인 포트 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                allValid = false
            }



            /**
            ===================================================================================================================================
            이 이후의 코드는 저장에 대한 작업을 수행합니다.
             */

            //최종적으로 모든 설정 값들에 대한 문제가 없으면 (allValid 변수가 True일 경우) 설정 값들을 저장합니다.
            if (allValid) {

                //연결에 사용되는 IP, PORT 1~2 의 정보를 저장하는 YAML 파일의 형식을 설정합니다.
                val data = mapOf("ip" to ip, "port" to portStr, "secondPort" to secondPortStr)
                val yaml = Yaml()
                val yamlString = yaml.dump(data)


                /*
                아래 코드는 YAML 파일의 저장 위치와 이름을 설정합니다.
                기본 위치(context?.filesDir) 는 View-> Tool Windows -> Device Explorer-> data-> data-> 프로젝트 패키지->files 에 위치하고 있습니다.
                필요시 위치를 변경할 수 있습니다. (아래 주석 처리된 코드는 기기의 download 디렉토리에 저장됩니다.)
                 */
                val file = File(context?.filesDir, "config.yaml")

                //Device Download Directory
                val downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                val file = File(downloadPath, "config.yaml")

                file.writeText(yamlString)

                //최종 저장이 끝났음을 토스트 메시지로 출력합니다.
                Toast.makeText(context, "Settings saved to YAML file!", Toast.LENGTH_SHORT).show()
            }

        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SetupSystemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SetupSystemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //IP의 유효성을 검사하는 필터입니다.
    class IpAddressInputFilter : InputFilter {
        // IP 주소의 각 부분을 검증하는 정규 표현식.
        // 1~3자리 숫자를 허용합니다.
        private val ipPartPattern = Pattern.compile("^\\d{1,3}$")

        // filter 메소드는 InputFilter 인터페이스에서 오버라이드해야 하는 메소드입니다.
        // 이 메소드는 사용자가 텍스트를 입력하거나 삭제할 때마다 호출됩니다.
        override fun filter(
            source: CharSequence,  // 새로 입력하거나 붙여넣은 텍스트
            start: Int,            // source의 시작 부분
            end: Int,              // source의 끝 부분
            dest: Spanned,         // 현재 EditText에 있는 텍스트
            dstart: Int,           // 새로운 텍스트가 추가될 시작 지점
            dend: Int              // 새로운 텍스트가 추가될 끝 지점
        ): CharSequence? {
            // 현재 EditText의 텍스트와 새로 입력된 텍스트를 결합합니다.
            val resultBuilder = StringBuilder(dest)
            resultBuilder.replace(dstart, dend, source.subSequence(start, end).toString())

            // 결합된 텍스트를 "."을 기준으로 분리하여 IP 주소의 각 부분을 얻습니다.
            val parts = resultBuilder.split(".")

            // 각 IP 주소 부분에 대한 검증을 수행합니다.
            for (part in parts) {
                // 부분이 비어있지 않고, 정규 표현식에 매치되지 않거나 255를 초과하는 경우
                if (part.isNotEmpty() && (!ipPartPattern.matcher(part).matches() || part.toInt() > 255)) {
                    // 입력을 거부하고 빈 문자열을 반환합니다.
                    return ""
                }
            }

            // 모든 검증을 통과하면 null을 반환하여 원래의 입력값을 그대로 사용합니다.
            return null
        }
    }



    // IP 주소의 유효성을 검사하는 함수입니다.
    private fun validateIpAddress(ip: String): Boolean {
        val parts = ip.split(".")
        if (parts.size == 4 && parts.all { it.isNotEmpty() && it.toInt() in 0..255 }) {
            return true
        }
        return false
    }

    // 포트 번호의 유효성을 검사하는 함수입니다.
    private fun validatePort(portStr: String): Boolean {
        return portStr.toIntOrNull()?.let { it in 0..65535 } ?: false
    }

}