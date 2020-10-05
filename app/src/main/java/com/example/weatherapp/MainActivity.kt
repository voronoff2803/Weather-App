package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val isDarkTheme: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        if (isDarkTheme) {
            theme.applyStyle(R.style.AppThemeDark, true)
        } else {
            theme.applyStyle(R.style.AppTheme, true)
        }

        setContentView(R.layout.activity_main)
    }
}