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



        val getIpEditText = binding.ipEditText
        val getPortEditText = binding.portEditText


        val saveBt = binding.systemSaveBt


        //IP입력창에 숫자와 . 만 입력되게 제한을 겁니다.
        getIpEditText.filters = arrayOf(IpAddressInputFilter())


        saveBt.setOnClickListener{

            var allValid = true

            val ip = getIpEditText.text.toString()
            val portStr = getPortEditText.text.toString()

            //입력된 IP 주소값의 범주가 정상적인지 확인하고 이를 YAML 형식으로 저장합니다.
            if (validateIpAddress(ip)) {
                Toast.makeText(context, "IP Address is valid!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "정상적인 IP주소 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                allValid = false
            }

            if (validatePort(portStr)) {
                Toast.makeText(context, "Port is valid!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "정상적인 포트 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                allValid = false
            }

            if (allValid) {
                val data = mapOf("ip" to ip, "port" to portStr)
                val yaml = Yaml()
                val yamlString = yaml.dump(data)

                // 파일 저장 위치와 이름을 지정합니다. 예: /data/user/0/패키지명/files/config.yaml
//                val file = File(context?.filesDir, "config.yaml")

                val downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = File(downloadPath, "config.yaml")
                file.writeText(yamlString)

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


    // 사용자 정의 InputFilter 클래스
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