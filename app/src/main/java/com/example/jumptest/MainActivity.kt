package com.example.jumptest

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jumptest.ui.theme.JumpTestTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JumpTestTheme {
                Content(viewModel)
            }
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel) {
    val context = LocalContext.current
    val color = viewModel.colorState.value
    val hexCode = viewModel.getColorHex()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "RGB Color",)

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(color)
            )

            Text(text = hexCode,)

            Spacer(modifier = Modifier.height(8.dp))

            ColorSlider(
                label = "Red",
                value = viewModel.red.value,
                onValueChange = { viewModel.updateRed(it) }
            )
            ColorSlider(
                label = "Green",
                value = viewModel.green.value,
                onValueChange = { viewModel.updateGreen(it) }
            )
            ColorSlider(
                label = "Blue",
                value = viewModel.blue.value,
                onValueChange = { viewModel.updateBlue(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Color", hexCode)
                    clipboard.setPrimaryClip(clip)


                    Toast.makeText(context, hexCode, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Copy")
            }
        }
    }
}

@Composable
fun ColorSlider(label: String, value: Float, onValueChange: (Float) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontWeight = FontWeight.SemiBold)
            Text(text = value.toInt().toString(), fontWeight = FontWeight.Bold)
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..255f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
