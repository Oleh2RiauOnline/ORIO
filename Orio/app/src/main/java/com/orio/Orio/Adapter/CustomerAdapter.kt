package com.orio.Orio.Adapter

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import com.orio.Orio.R
import com.orio.Orio.db.Customer
import com.orio.Orio.Admin.show_customer

class CustomerAdapter (val mCtx: Context, val layoutResId: Int, val list: List<Customer> )
    : ArrayAdapter<Customer>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textEmail = view.findViewById<TextView>(R.id.textEmail)
        val textPassword = view.findViewById<TextView>(R.id.textPassword)
        val textUpdate = view.findViewById<ImageView>(R.id.TextUpdate)
        val textDelete = view.findViewById<ImageView>(R.id.TextDelete)
        val user = list[position]

        textEmail.text = user.email
        textPassword.text = user.password

        textUpdate.setOnClickListener {
            showUpdateDialog(user)
        }
        textDelete.setOnClickListener {
            Deleteinfo(user)
        }

        return view

    }

    private fun Deleteinfo(customer: Customer) {
        val progressDialog = ProgressDialog(context,
                R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting…")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("customer")
        mydatabase.child(customer.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, show_customer::class.java)
        context.startActivity(intent)
    }



    private fun showUpdateDialog(customer: Customer) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.activity_update_customer, null)

        val textemail = view.findViewById<EditText>(R.id.upEmail)
        val textpassword = view.findViewById<EditText>(R.id.upPassword)

        textemail.setText(customer.email)
        textpassword.setText(customer.password)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("customer")

            val email = textemail.text.toString().trim()

            val password = textpassword.text.toString().trim()

            if (email.isEmpty()){
                textemail.error = "isi kolom email"
                textemail.requestFocus()
                return@setPositiveButton
            }

            if (password.isEmpty()){
                textpassword.error = "isi kolom password"
                textpassword.requestFocus()
                return@setPositiveButton
            }

            val customer = Customer(customer.id,email,password)

            dbUsers.child(customer.id).setValue(customer).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()
    }
}

