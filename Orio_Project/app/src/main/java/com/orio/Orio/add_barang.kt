package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.orio.Orio.db.Barang

class add_barang: AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_barang)

        ref = FirebaseDatabase.getInstance().getReference("barang")

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, show_barang::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val inputNama = findViewById<TextInputEditText>(R.id.inputNama)
        val inputKuant = findViewById<TextInputEditText>(R.id.inputKuant)
        val inputHarga = findViewById<TextInputEditText>(R.id.inputHarga)
        val nama = inputNama.text.toString()
        val kuant = inputKuant.text.toString()
        val harga = inputHarga.text.toString()

        val barangId = ref.push().key.toString()
        val barang = Barang(barangId,nama,kuant,harga)

        ref.child(barangId).setValue(barang).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputKuant.setText("")
            inputHarga.setText("")
        }
    }
}