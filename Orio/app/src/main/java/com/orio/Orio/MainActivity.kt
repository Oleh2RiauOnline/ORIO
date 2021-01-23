package com.orio.Orio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id. btn_maps)
        button.setOnClickListener{
            val intent = Intent(this, Mapsoleh2::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<Button>(R.id. btn_regis)
        button1.setOnClickListener{
            val intent1 = Intent(this, Register::class.java)
            startActivity(intent1)
        }
    }
}