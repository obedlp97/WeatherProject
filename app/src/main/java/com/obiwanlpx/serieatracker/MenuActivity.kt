package com.obiwanlpx.serieatracker

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import kotlin.math.sign

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val resultsButton = findViewById<Button>(R.id.resultsButton)
        signUpButton.setOnClickListener { navigateToSignUPButton() }
        resultsButton.setOnClickListener{ navigateToResultsButton () }
    }

    fun navigateToSignUPButton() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun navigateToResultsButton() {
        val intent = Intent(this, resultsjuventus.resultsJuventus::class.java)
        startActivity(intent)
    }
}