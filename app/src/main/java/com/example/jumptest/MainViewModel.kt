package com.example.jumptest

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class MainViewModel {

    private val _colorState = MutableLiveData(Color.Black)

    val colorState: LiveData<Color> = _colorState


    fun updateRed(value: Float) {

    }

    fun updateBlue(value: Float) {

    }

    fun updateGreen(value: Float) {

    }


}