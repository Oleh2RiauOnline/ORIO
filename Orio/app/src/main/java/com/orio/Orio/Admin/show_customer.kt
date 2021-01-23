package com.orio.Orio.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.orio.Orio.Adapter.CustomerAdapter
import com.orio.Orio.R
import com.orio.Orio.db.Customer

class show_customer :AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Customer>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_customer)

        ref = FirebaseDatabase.getInstance().getReference("customer")
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
                        val customer = h.getValue(Customer::class.java)
                        list.add(customer!!)
                    }
                    val adapter = CustomerAdapter(this@show_customer, R.layout.customers,list)
                    listView.adapter = adapter
                }
            }
        })

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            val intent = Intent (this, add_customer::class.java)
            startActivity(intent)
        }
    }
}