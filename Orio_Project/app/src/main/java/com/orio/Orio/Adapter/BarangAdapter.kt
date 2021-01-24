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
import com.orio.Orio.db.Barang
import com.orio.Orio.show_barang

class BarangAdapter (val mCtx: Context, val layoutResId: Int, val list: List<Barang> )
    : ArrayAdapter<Barang>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textKuant = view.findViewById<TextView>(R.id.textKuant)
        val textHarga = view.findViewById<TextView>(R.id.textHarga)
        val textUpdate = view.findViewById<ImageView>(R.id.TextUpdate)
        val textDelete = view.findViewById<ImageView>(R.id.TextDelete)
        val user = list[position]

        textNama.text = user.nama
        textKuant.text = user.kuantitas
        textHarga.text = user.harga


        textUpdate.setOnClickListener {
            showUpdateDialog(user)
        }
        textDelete.setOnClickListener {
            Deleteinfo(user)
        }

        return view

    }

    private fun Deleteinfo(barang: Barang) {
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting…")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("barang")
        mydatabase.child(barang.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, show_barang::class.java)
        context.startActivity(intent)
    }



    private fun showUpdateDialog(barang: Barang) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.activity_update_barang, null)

        val textnama = view.findViewById<EditText>(R.id.upNama)
        val textkuant = view.findViewById<EditText>(R.id.upKuant)
        val textharga = view.findViewById<EditText>(R.id.upHarga)


        textnama.setText(barang.nama)
        textkuant.setText(barang.kuantitas)
        textharga.setText(barang.harga)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("barang")

            val nama = textnama.text.toString().trim()

            val kuant = textkuant.text.toString().trim()

            val harga = textharga.text.toString().trim()



            if (nama.isEmpty()){
                textnama.error = "isi kolom nama"
                textnama.requestFocus()
                return@setPositiveButton
            }

            if (kuant.isEmpty()){
                textkuant.error = "isi kolom kuantitas"
                textkuant.requestFocus()
                return@setPositiveButton
            }
            if (harga.isEmpty()){
                textharga.error = "isi kolom harga"
                textharga.requestFocus()
                return@setPositiveButton
            }


            val barang = Barang(barang.id,nama,kuant,harga)

            dbUsers.child(barang.id).setValue(barang).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()
    }
}