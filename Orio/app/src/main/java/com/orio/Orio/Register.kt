package com.orio.Orio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class Register : AppCompatActivity() , View.OnClickListener{
    private lateinit var edemail: EditText
    private lateinit var edpassword: EditText
    private lateinit var btnregis: Button
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_regis)

        ref = FirebaseDatabase.getInstance().getReference("customer")

        edemail = findViewById(R.id.et_email)
        edpassword = findViewById(R.id.et_password)
        btnregis = findViewById(R.id.btn_register)
        

        btnregis.setOnClickListener(this)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (a in snapshot.children) {
                        val customer = a.getValue(CustomerAdapter::class.java)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onClick(v: View?) {
        simpanData()
    }

    private fun simpanData() {
        val email = edemail.text.toString().trim()
        val password = edpassword.text.toString()

        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(this, "Isi data secara lengkap tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val customerId = ref.push().key
        val customer = CustomerAdapter(customerId!!, email, password)

        ref.child(customerId).setValue(customer).addOnCompleteListener {
            Toast.makeText(
                applicationContext,
                "Selamat, Anda sudah mempunyai akun", Toast.LENGTH_SHORT
            ).show()
        }
    }
}