package com.example.db

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class CustomerAdapter(
    val CustomerContext: Context,
    val layoutResId:Int,
    val customerList: List<Customer>
): ArrayAdapter<Customer>(CustomerContext, layoutResId,customerList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(CustomerContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val o_email: TextView = view.findViewById(R.id.ou_email)
        val o_password: TextView = view.findViewById(R.id.ou_password)
        val imgEdit: ImageView = view.findViewById(R.id.icn_edit)

        val customer = customerList[position]

        imgEdit.setOnClickListener {
            updateDialog(customer)
        }

        o_email.text = "Email : " + customer.email
        o_password.text = "Password  : " + customer.password

        return view
    }
    private fun updateDialog(customer: Customer){
        val builder = AlertDialog.Builder(CustomerContext)
        builder.setTitle("Update Data")
        val inflater = LayoutInflater.from(CustomerContext)
        val view = inflater.inflate(R.layout.update, null)

        val edtemail = view.findViewById<EditText>(R.id.upemail)
        val edtpassword = view.findViewById<EditText>(R.id.uppassword)

        edtemail.setText(customer.email)
        edtpassword.setText(customer.password)

        builder.setView(view)

        builder.setPositiveButton("Ubah"){ p0, p1 ->
            val dbCustomer = FirebaseDatabase.getInstance().getReference("Customer")
            val email = edtemail.text.toString().trim()
            val password = edtpassword.text.toString().trim()
            if(email.isEmpty() or password.isEmpty() ) {
                Toast.makeText(CustomerContext, "Isi data secara lengkap tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            val customer = Customer(customer.id,email,password)

            dbCustomer.child(customer.id).setValue(customer)
            Toast.makeText(CustomerContext,"Data berhasil di update",Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Batal"){ p0, p1 ->}

        builder.setNegativeButton("Hapus") { p0, p1 ->
            val dbCustomer = FirebaseDatabase.getInstance().getReference("Customer").child(customer.id)

            dbCustomer.removeValue()

            Toast.makeText(CustomerContext, "Data berhasil di hapus",Toast.LENGTH_SHORT).show()
        }

        val alert = builder.create()
        alert.show()
    }
}

