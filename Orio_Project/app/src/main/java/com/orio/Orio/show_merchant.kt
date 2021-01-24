package com.orio.Orio

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.orio.Orio.Adapter.MerchantAdapter
import com.orio.Orio.db.Merchant

class show_merchant :AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Merchant>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_merchant)

        ref = FirebaseDatabase.getInstance().getReference("merchant")
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
                        val merchant = h.getValue(Merchant::class.java)
                        list.add(merchant!!)
                    }
                    val adapter = MerchantAdapter(this@show_merchant, R.layout.merchants,list)
                    listView.adapter = adapter
                }
            }
        })

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            val intent = Intent (this, add_merchant::class.java)
            startActivity(intent)
        }
    }
}