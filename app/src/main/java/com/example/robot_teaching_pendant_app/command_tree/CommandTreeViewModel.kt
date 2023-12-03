package com.example.robot_teaching_pendant_app.command_tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommandTreeViewModel: ViewModel() {

    //MakeDefaultFragment에 있는 아이콘 버튼을 클릭하면 TextView(UI 요소)를 업데이트 해주는 이벤트입니다.
    private val _updateTextViewEvent = MutableLiveData<Unit>()
    val updateTextViewEvent: LiveData<Unit> get() = _updateTextViewEvent

    //ViewModel에서 데이터 변경 이벤트를 발생시키는 트리거 역할을 합니다.
    fun triggerUpdateTextViewEvent() {
        _updateTextViewEvent.value = Unit
    }






}