package com.obiwanlpx.serieatracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.obiwanlpx.serieatracker.R
import android.widget.TextView
import android.content.Intent

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val nameEntered: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text = "Hola $nameEntered"
    }
}