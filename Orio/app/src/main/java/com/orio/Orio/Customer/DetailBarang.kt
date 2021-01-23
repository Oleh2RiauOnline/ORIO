package com.orio.Orio.Customer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.orio.Orio.R

class DetailBarang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_olehdetail)

        val btn = findViewById<Button>(R.id.map_toko)
        btn.setOnClickListener{
            val intent = Intent(this, Maps::class.java)
            startActivity(intent)
        }

    }


}