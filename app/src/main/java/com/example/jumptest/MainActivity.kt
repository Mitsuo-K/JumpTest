package com.example.jumptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jumptest.ui.theme.JumpTestTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JumpTestTheme {
                Content(viewmodel)
            }
        }
    }
}

@Composable
fun Content(viewmodel: MainViewModel) {

    viewmodel.colorState.observe(this){

    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Black)
            )

            Spacer(modifier = Modifier.height(6.dp))

            ColorSlider("Red" , "000",{
                viewmodel.updateRed(it)
            })
            ColorSlider("Green", "000", {
                viewmodel.updateGreen(it)
            })
            ColorSlider("Blue" ,"000", {
                viewmodel.updateBlue(it)
            })

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = {}
            ) {
                Text("Copy")
            }
        }
    }
}

@Composable
fun ColorSlider(color:String , value:String , valueChange: (value: Float) -> Unit = {_ -> }) {
    //text - text
    //(Slider)
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(color)
            Spacer(modifier = Modifier.width(6.dp))
            Text(value)
        }
        Slider(
            value = 0f,
            onValueChange = {valueChange(it)},
            valueRange = 0f..255f,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JumpTestTheme {
        Content(viewmodel)
    }
}