package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login: AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val username = findViewById<TextInputEditText>(R.id.lgnusername)
        val password = findViewById<TextInputEditText>(R.id.lgnPassword)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        btnlogin.setOnClickListener {

            if (username.text.toString().equals("Admin") && password.text.toString().equals("Admin")){
                val intent = Intent (this, MainActivity::class.java)
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Bisa", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }

        }
    }
}