package com.example.db

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var edemail : EditText
    private lateinit var edpassword : EditText
    private lateinit var btnRegister : Button
    private lateinit var listData : ListView
    private lateinit var ref : DatabaseReference
    private lateinit var CustomerList : MutableList<Customer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("customer")

        edemail = findViewById(R.id.edt_email)
        edpassword = findViewById(R.id.edt_password)
        btnRegister = findViewById(R.id.btnregister)
        listData = findViewById(R.id.lv_hasil)

        btnRegister.setOnClickListener(this)
        CustomerList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    CustomerList.clear()
                    for (a in snapshot.children){
                        val customer = a.getValue(Customer::class.java)
                        if (customer != null){
                            CustomerList.add(customer)
                        }
                    }
                    val adapter = CustomerAdapter(this@MainActivity, R.layout.list_customer, CustomerList)
                    listData.adapter = adapter

                    println("Output : "+CustomerList)
                }
            }

            override fun onCancelled(error: DatabaseError){
                TODO("Not yet implemented")
            }
        })
    }

    override fun onClick(v: View?){
        simpanData()
    }

    private fun simpanData(){
        val email = edemail.text.toString().trim()
        val password = edpassword.text.toString()

        if(email.isEmpty() or password.isEmpty()){
            Toast.makeText(this,"Isi data secara lengkap tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        val customerId = ref.push().key
        val customer = Customer(customerId!!, email,password)

        ref.child(customerId).setValue(customer).addOnCompleteListener { Toast.makeText(applicationContext,
                "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }
}