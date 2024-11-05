package com.obiwanlpx.serieatracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.obiwanlpx.serieatracker.ui.theme.SerieATrackerTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import android.content.Intent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startText = findViewById<AppCompatButton>(R.id.startText)
        val etName = findViewById<AppCompatEditText>(R.id.etName)

        startText.setOnClickListener {
            val nameEntered = etName.text.toString()

            if (nameEntered.isNotEmpty()) {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("EXTRA_NAME", nameEntered)
                startActivity(intent)
            }
        }
    }
}