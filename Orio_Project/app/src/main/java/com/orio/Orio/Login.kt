package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login: AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val email = findViewById<TextView>(R.id.lgnEmail).toString()
        val password = findViewById<TextView>(R.id.lgnPassword).toString()
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val intent = Intent (this, Login::class.java)
            startActivity(intent)
        }
    }
}