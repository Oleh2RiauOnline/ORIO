package com.orio.Orio.Customer

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.orio.Orio.R
import kotlinx.android.synthetic.main.activity_show_customer.*
import kotlinx.android.synthetic.main.orio_keranjang_customer.*
import kotlinx.android.synthetic.main.riwayat_customer.*

class RiwayatPembelian : AppCompatActivity() {
    val history = arrayOf(
        "Kue Bangkit",
        "Talam Durian",
        "Kue Bolu",
        "Kue Lapis",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.riwayat_customer)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history)
        list_keranjang.adapter = arrayAdapter
        list_keranjang.setOnItemClickListener{ parent: AdapterView<*>?, view: View?, posisi: Int, id: Long ->
            Toast.makeText(
                this, "Klik id: " + (posisi+1) + " " +
                        history[posisi], Toast.LENGTH_SHORT
            ).show()
        }

    }


}