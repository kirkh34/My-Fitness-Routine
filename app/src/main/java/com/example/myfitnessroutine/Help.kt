package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)


        val btnHelpGoBack = findViewById<Button>(R.id.helpGoBack)
        btnHelpGoBack.setOnClickListener{
            this.finish()
        }
    }
}