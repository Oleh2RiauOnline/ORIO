package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.orio.Orio.Adapter.TokoAdapter
import com.orio.Orio.db.Toko

class show_toko :AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Toko>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_toko)

        ref = FirebaseDatabase.getInstance().getReference("toko")
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
                        val toko = h.getValue(Toko::class.java)
                        list.add(toko!!)
                    }
                    val adapter = TokoAdapter(this@show_toko, R.layout.toko,list)
                    listView.adapter = adapter
                }
            }
        })

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            val intent = Intent (this, add_toko::class.java)
            startActivity(intent)
        }
    }
}