package com.orio.Orio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.riwayat_customer.*

class listRiwayat : AppCompatActivity(){

    val riwayat = arrayOf(
        "Pisang Keju",
        "Kembojo",
        "Kripik Pisang",
        "Makanan enak",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val arrayAdapter = ArrayAdapter(this, 
            android.R.layout.simple_list_item_1, riwayat)
        list_riwayat.adapter = arrayAdapter
        
        list_riwayat.setOnItemClickListener { parent : AdapterView<*>?, view : View?,
                                              position : Int, id : Long->
            Toast.makeText(
                this, "Klik di "+ (position + 1) + " "+
                        riwayat[position], Toast.LENGTH_SHORT
            ).show()
        }
    }
}