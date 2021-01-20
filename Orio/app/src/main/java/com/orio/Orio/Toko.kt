package com.orio.Orio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class Toko : AppCompatActivity() , View.OnClickListener{
    private lateinit var ednama: EditText
    private lateinit var edalamat: EditText
    private lateinit var eddeskripsi: EditText
    private lateinit var btntambah: Button
    private lateinit var ref: DatabaseReference
    private lateinit var edlat : EditText
    private lateinit var edlong: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_regis)

        ref = FirebaseDatabase.getInstance().getReference("customer")

        ednama = findViewById(R.id.editTextTextPersonName2)
        edalamat = findViewById(R.id.editTextTextPersonName3)
        btntambah = findViewById(R.id.button2)
        eddeskripsi = findViewById(R.id.editTextTextPersonName4)
        edlat = findViewById(R.id.editTextTextPersonName7)
        edlong = findViewById(R.id.editTextTextPersonName8)


        btntambah.setOnClickListener(this)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (a in snapshot.children) {
                        val toko = a.getValue(CustomerAdapter::class.java)

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
        val nama = ednama.text.toString().trim()
        val alamat = edalamat.text.toString()
        val deskripsi = eddeskripsi.text.toString()
        val lat = edlat.text.toString()
        val long = edlong.text.toString()

        if (nama.isEmpty() or alamat.isEmpty() or deskripsi.isEmpty() or lat.isEmpty() or long.isEmpty()) {
            Toast.makeText(this, "Isi data secara lengkap tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val tokoId = ref.push().key
        val toko = TokoAdapter(tokoId!!, nama, alamat, deskripsi, lat, long)

        ref.child(tokoId).setValue(toko).addOnCompleteListener {
            Toast.makeText(
                applicationContext,
                "Selamat, Toko Anda sudah terdaftar", Toast.LENGTH_SHORT
            ).show()
        }
    }
}