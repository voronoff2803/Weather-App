package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isDarkTheme: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        if (savedInstanceState != null) {
            isDarkTheme = savedInstanceState.getBoolean("theme")
        }

        if (isDarkTheme) {
            theme.applyStyle(R.style.AppThemeDark, true)
        } else {
            theme.applyStyle(R.style.AppTheme, true)
        }

        setContentView(R.layout.activity_main)
    }

    fun changeTheme(view: View) {
        isDarkTheme = !isDarkTheme
        recreate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("theme", isDarkTheme)
    }
}