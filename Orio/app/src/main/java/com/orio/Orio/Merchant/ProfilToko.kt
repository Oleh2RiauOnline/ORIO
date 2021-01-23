package com.orio.Orio.Merchant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.orio.Orio.R

class ProfilToko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_informasi_toko)

        val logout = findViewById<Button>(R.id.logout_toko)
        logout.setOnClickListener{
            val intent = Intent(this, LoginMerchant::class.java)
            startActivity(intent)
        }
        val history = findViewById<Button>(R.id.history_pesan)
        history.setOnClickListener{
            val intent = Intent(this, HistoryMerchant::class.java)
            startActivity(intent)
        }
        val layanan = findViewById<Button>(R.id.penerima_pesanan)
        layanan.setOnClickListener{
            val intent = Intent(this, PesananMerchant::class.java)
            startActivity(intent)
        }

    }


}