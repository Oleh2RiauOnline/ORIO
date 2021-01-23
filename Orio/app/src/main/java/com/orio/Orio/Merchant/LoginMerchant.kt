package com.orio.Orio.Merchant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.orio.Orio.R

class LoginMerchant : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_login_toko)

        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener{
            val intent = Intent(this, ProfilToko::class.java)
            startActivity(intent)
        }

        val textView = findViewById<TextView>(R.id.regismerchant)
        textView.setOnClickListener{
            val intent1 = Intent(this, RegisterMerchant::class.java)
            startActivity(intent1)
        }


    }


}