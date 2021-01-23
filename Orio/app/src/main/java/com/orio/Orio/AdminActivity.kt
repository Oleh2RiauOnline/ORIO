package com.orio.Orio

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val customer_btn = findViewById<ImageButton>(R.id.imageButton1)
        val merchant_btn = findViewById<ImageButton>(R.id.imageButton2)
        val toko_btn = findViewById<ImageButton>(R.id.imageButton3)
        val pesanan_btn = findViewById<ImageButton>(R.id.imageButton4)

        customer_btn.setOnClickListener{
            val intent = Intent (this, show_customer::class.java)
            startActivity(intent)
        }
    }


}