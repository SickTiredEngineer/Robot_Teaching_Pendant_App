package com.example.robot_teaching_pendant_app.make

import android.graphics.drawable.Icon
import android.widget.Toast
import com.example.robot_teaching_pendant_app.R

data class Icon(val imageRes: Int, val title: String, val action:() -> Unit)


val allIcons = listOf(
    Icon(R.drawable.mybticon, "Move", { }),
    Icon(R.drawable.mybticon, "동작J", { /* 동작 처리 */ }),
    Icon(R.drawable.mybticon, "동작L", { /* 동작 처리 */ }),
    Icon(R.drawable.mybticon, "Circle", { /* 동작 처리 */ }),
    Icon(R.drawable.mybticon, "Wait", { /* 동작 처리 */ }),

    // ... other icons for '전체' category
)

val moveIcons = listOf(
    Icon(R.drawable.mybticon, "동작J", { /* 동작 처리 */ }),
    Icon(R.drawable.mybticon, "동작L", { /* 동작 처리 */ }),
    // ... other icons for '이동' category
)