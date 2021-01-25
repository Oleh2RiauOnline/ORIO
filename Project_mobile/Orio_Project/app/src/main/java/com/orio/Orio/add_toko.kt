package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.orio.Orio.db.Toko

class add_toko: AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_toko)

        ref = FirebaseDatabase.getInstance().getReference("toko")

        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, show_toko::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val inputNama = findViewById<TextInputEditText>(R.id.inputNama)
        val inputAlamat = findViewById<TextInputEditText>(R.id.inputAlamat)
        val nama = inputNama.text.toString()
        val alamat = inputAlamat.text.toString()

        val tokoId = ref.push().key.toString()
        val toko = Toko(tokoId,nama,alamat)

        ref.child(tokoId).setValue(toko).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputAlamat.setText("")
        }
    }
}