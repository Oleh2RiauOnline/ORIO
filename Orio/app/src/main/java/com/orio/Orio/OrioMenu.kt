package com.orio.Orio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class OrioMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_menu)
        val imageView1 = findViewById<ImageView>(R.id.toko1)
        imageView1.setOnClickListener{
            val intent = Intent(this, DetailBarang::class.java)
            startActivity(intent)
        }
        val imageView2 = findViewById<ImageView>(R.id.toko2)
        imageView2.setOnClickListener{
            val intent = Intent(this, DetailBarang::class.java)
            startActivity(intent)
        }
        val imageView3 = findViewById<ImageView>(R.id.toko3)
        imageView3.setOnClickListener{
            val intent = Intent(this, DetailBarang::class.java)
            startActivity(intent)
        }
        val imageView4 = findViewById<ImageView>(R.id.toko4)
        imageView4.setOnClickListener{
            val intent = Intent(this, DetailBarang::class.java)
            startActivity(intent)
        }
        val imageView5 = findViewById<ImageView>(R.id.toko5)
        imageView5.setOnClickListener{
            val intent = Intent(this, DetailBarang::class.java)
            startActivity(intent)
        }
        val imageView6 = findViewById<ImageView>(R.id.toko6)
        imageView6.setOnClickListener{
            val intent = Intent(this, DetailBarang::class.java)
            startActivity(intent)
        }
        val buttonex = findViewById<Button>(R.id.logout)
        buttonex.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val buttonhis = findViewById<Button>(R.id.riwayat)
        buttonhis.setOnClickListener{
            val intent = Intent(this, RiwayatPembelian::class.java)
            startActivity(intent)
        }
        val buttonker = findViewById<Button>(R.id.keranjang)
        buttonker.setOnClickListener{
            val intent = Intent(this, KeranjangPembelian::class.java)
            startActivity(intent)
        }


    }


}