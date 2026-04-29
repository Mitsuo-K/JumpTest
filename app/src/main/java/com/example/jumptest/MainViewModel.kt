package com.example.jumptest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _colorState = mutableStateOf(Color.Black)
    val colorState: State<Color> = _colorState

    private val _red = mutableStateOf(0f)
    val red: State<Float> = _red

    private val _green = mutableStateOf(0f)
    val green: State<Float> = _green

    private val _blue = mutableStateOf(0f)
    val blue: State<Float> = _blue

    fun updateRed(value: Float) {
        _red.value = value
        updateColorState()
    }

    fun updateGreen(value: Float) {
        _green.value = value
        updateColorState()
    }

    fun updateBlue(value: Float) {
        _blue.value = value
        updateColorState()
    }

    private fun updateColorState() {
        _colorState.value = Color(
            red = _red.value / 255f,
            green = _green.value / 255f,
            blue = _blue.value / 255f
        )
    }
    fun getColorHex(): String {
        val r = _red.value.toInt()
        val g = _green.value.toInt()
        val b = _blue.value.toInt()
        return String.format("#%02X%02X%02X", r, g, b)
    }
}
