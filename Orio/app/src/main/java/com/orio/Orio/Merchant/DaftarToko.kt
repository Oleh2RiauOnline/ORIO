package com.orio.Orio.Merchant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.orio.Orio.R

class DaftarToko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_informasi_toko)

        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener{
            val intent = Intent(this, ProfilToko::class.java)
            startActivity(intent)
        }

    }


}