package com.orio.Orio.Merchant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.orio.Orio.R

class RegisterMerchant : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.regis_merchant)

        val button = findViewById<Button>(R.id.btn_register_merchant)
        button.setOnClickListener{
            val intent = Intent(this, DaftarToko::class.java)
            startActivity(intent)
        }


    }


}