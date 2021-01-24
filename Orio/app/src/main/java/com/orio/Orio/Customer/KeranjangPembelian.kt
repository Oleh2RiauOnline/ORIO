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

class KeranjangPembelian : AppCompatActivity() {
    val pembelian = arrayOf(
        "Kue Bangkit 22-01-2021",
        "Talam Durian 22-04-2021",
        "Kue Bolu 02-03-2021",
        "Kue Lapis 22-01-2021",
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.orio_keranjang_customer)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pembelian)
        list_keranjang.adapter = arrayAdapter
        list_keranjang.setOnItemClickListener{ parent: AdapterView<*>?, view: View?, posisi: Int, id: Long ->
            Toast.makeText(
                this, "Klik id: " + (posisi+1) + " " +
                        pembelian[posisi], Toast.LENGTH_SHORT
            ).show()
        }

    }


}