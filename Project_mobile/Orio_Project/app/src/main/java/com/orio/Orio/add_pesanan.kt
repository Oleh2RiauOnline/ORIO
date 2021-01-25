package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.orio.Orio.db.Pesanan

class add_pesanan : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pesanan)

        ref = FirebaseDatabase.getInstance().getReference("pesanan")

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, show_pesanan::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val inputTanggal = findViewById<TextInputEditText>(R.id.inputTanggal)
        val inputBarang = findViewById<TextInputEditText>(R.id.inputBarang)
        val tanggal = inputTanggal.text.toString()
        val barang = inputBarang.text.toString()

        val pesananId = ref.push().key.toString()
        val pesanan = Pesanan(pesananId,tanggal,barang)

        ref.child(pesananId).setValue(pesanan).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            inputTanggal.setText("")
            inputBarang.setText("")
        }
    }
}