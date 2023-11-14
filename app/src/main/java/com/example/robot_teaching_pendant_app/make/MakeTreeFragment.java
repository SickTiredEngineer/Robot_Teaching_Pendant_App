package com.example.robot_teaching_pendant_app.make;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.robot_teaching_pendant_app.R;
import com.example.robot_teaching_pendant_app.databinding.MakeTreeFragmentBinding;

import java.util.ArrayList;

public class MakeTreeFragment extends Fragment {

    private MakeTreeFragmentBinding binding;
    private SQLiteHelper sqLiteHelper;
    private TextView textView;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = MakeTreeFragmentBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.make_tree_fragment, container, false);

        sqLiteHelper = new SQLiteHelper(getActivity()); // getActivity()를 사용하여 컨텍스트를 가져옴
        ArrayList<String> nameList = sqLiteHelper.getMemberNames();

        textView = view.findViewById(R.id.testView);
        textView.setText(nameList.toString());
        return view;

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("INSERT INTO Members VALUES (4,'Sin',99);");
                Log.d("Database", "작동완료");
                sqLiteDatabase.close(); // 사용이 끝난 후에는 반드시 닫아야 합니다.
                //명령어 식별자
                //순서 확인
                //화위 명령어수
                //화위 명령어
                //하위 명령어 순서
                //화위 명령어2
                //하위 명령어 순서2
                //화위 명령어3
                //하위 명령어 순서3
                //화위 명령어4
                //하위 명령어 순서4
                //화위 명령어5
                //하위 명령어 순서5
                //화위 명령어6
                //하위 명령어 순서6
                //화위 명령어7
                //하위 명령어 순서7
                //화위 명령어8
                //하위 명령어 순서8
                //화위 명령어9
                //하위 명령어 순서9

            }
        });

    }



}
