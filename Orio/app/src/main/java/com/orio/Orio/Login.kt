package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_login)

        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener{
            val intent = Intent(this, OrioMenu::class.java)
            startActivity(intent)
        }

        val textView = findViewById<TextView>(R.id.daftarregis)
        textView.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


    }


}