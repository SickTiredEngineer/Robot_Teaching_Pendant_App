package com.example.robot_teaching_pendant_app.command_tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommandTreeViewModel: ViewModel() {

    private val _updateEvent = MutableLiveData<Unit>()
    val updateEvent: LiveData<Unit> get() = _updateEvent

    // 이벤트 트리거 메서드
    fun triggerUpdateEvent() {
        _updateEvent.value = Unit
    }


}