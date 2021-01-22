package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_regis)

        val button = findViewById<Button>(R.id.btn_register)
        button.setOnClickListener{
            val intent = Intent(this, OrioMenu::class.java)
            startActivity(intent)
        }


    }


}