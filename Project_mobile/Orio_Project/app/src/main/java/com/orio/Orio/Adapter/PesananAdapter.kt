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
import com.orio.Orio.db.Pesanan
import com.orio.Orio.show_pesanan

class PesananAdapter (val mCtx: Context, val layoutResId: Int, val list: List<Pesanan> )
    : ArrayAdapter<Pesanan>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textTanggal = view.findViewById<TextView>(R.id.textTanggal)
        val textBarang = view.findViewById<TextView>(R.id.textBarang)
        val textUpdate = view.findViewById<ImageView>(R.id.TextUpdate)
        val textDelete = view.findViewById<ImageView>(R.id.TextDelete)
        val user = list[position]

        textTanggal.text = user.tanggal
        textBarang.text = user.barang

        textUpdate.setOnClickListener {
            showUpdateDialog(user)
        }
        textDelete.setOnClickListener {
            Deleteinfo(user)
        }

        return view

    }

    private fun Deleteinfo(pesanan: Pesanan) {
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting…")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("pesanan")
        mydatabase.child(pesanan.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, show_pesanan::class.java)
        context.startActivity(intent)
    }



    private fun showUpdateDialog(pesanan: Pesanan) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.activity_update_pesanan, null)

        val texttanggal = view.findViewById<EditText>(R.id.upTanggal)
        val textbarang = view.findViewById<EditText>(R.id.upBarang)

        texttanggal.setText(pesanan.tanggal)
        textbarang.setText(pesanan.barang)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("pesanan")

            val tanggal = texttanggal.text.toString().trim()

            val barang = textbarang.text.toString().trim()

            if (tanggal.isEmpty()){
                texttanggal.error = "isi kolom tanggal"
                texttanggal.requestFocus()
                return@setPositiveButton
            }

            if (barang.isEmpty()){
                textbarang.error = "isi kolom barang"
                textbarang.requestFocus()
                return@setPositiveButton
            }

            val pesanan = Pesanan(pesanan.id,tanggal,barang)

            dbUsers.child(pesanan.id).setValue(pesanan).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()
    }
}