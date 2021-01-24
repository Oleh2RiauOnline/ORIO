package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.orio.Orio.Adapter.PesananAdapter
import com.orio.Orio.db.Pesanan

class show_pesanan :AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Pesanan>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_pesanan)

        ref = FirebaseDatabase.getInstance().getReference("pesanan")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val pesanan = h.getValue(Pesanan::class.java)
                        list.add(pesanan!!)
                    }
                    val adapter = PesananAdapter(this@show_pesanan, R.layout.merchants,list)
                    listView.adapter = adapter
                }
            }
        })

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            val intent = Intent (this, add_pesanan::class.java)
            startActivity(intent)
        }
    }
}