package com.orio.Orio.Adapter

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.orio.Orio.R
import com.orio.Orio.db.Merchant
import com.orio.Orio.show_merchant

class MerchantAdapter (val mCtx: Context, val layoutResId: Int, val list: List<Merchant> )
    : ArrayAdapter<Merchant>(mCtx,layoutResId,list){

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

    private fun Deleteinfo(merchant: Merchant) {
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting…")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("merchant")
        mydatabase.child(merchant.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, show_merchant::class.java)
        context.startActivity(intent)
    }



    private fun showUpdateDialog(merchant: Merchant) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.activity_update_merchant, null)

        val textemail = view.findViewById<EditText>(R.id.upEmail)
        val textpassword = view.findViewById<EditText>(R.id.upPassword)

        textemail.setText(merchant.email)
        textpassword.setText(merchant.password)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("merchant")

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

            val merchant = Merchant(merchant.id,email,password)

            dbUsers.child(merchant.id).setValue(merchant).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()
    }
}