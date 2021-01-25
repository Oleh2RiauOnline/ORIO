package com.orio.customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LoginActivity : AppCompatActivity() {
    val pilih = arrayListOf(
            "customer",
            "merchant"
    )
    var teks:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pilih)
        val pilih = findViewById<Spinner>(R.id.pilihan)
        pilih.adapter = arrayAdapter


        pilih.setOnItemSelectedListener(object : OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                 teks = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }

        })

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)

        val btn_login = findViewById<Button>(R.id.loginbtn)
        val intent = Intent(this, MainActivity::class.java)
        val databaseReference = FirebaseDatabase.getInstance().getReference()



        btn_login.setOnClickListener(){
            databaseReference.child(teks).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var cek: Boolean = false

                    for (data in dataSnapshot.getChildren()) {
                        if (data.child("email").getValue(String::class.java).equals(email.text.toString())) {
                            if (data.child("password").getValue(String::class.java).equals(password.text.toString())) {
                                startActivity(intent)
                                cek = true
                            } else {
                                android.widget.Toast.makeText(this@LoginActivity, "password anda salah", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    if (cek == false) {
                        android.widget.Toast.makeText(this@LoginActivity, teks, android.widget.Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")

                }
            })

        }
    }
}