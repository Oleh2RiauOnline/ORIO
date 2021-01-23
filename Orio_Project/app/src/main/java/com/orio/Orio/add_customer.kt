package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.orio.Orio.db.Customer

class add_customer: AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        ref = FirebaseDatabase.getInstance().getReference("customer")

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, show_customer::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val inputEmail = findViewById<TextInputEditText>(R.id.inputEmail)
        val inputPassword = findViewById<TextInputEditText>(R.id.inputPassword)
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()

        val customerId = ref.push().key.toString()
        val customer = Customer(customerId,email,password)

        ref.child(customerId).setValue(customer).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            inputEmail.setText("")
            inputPassword.setText("")
        }
    }
}