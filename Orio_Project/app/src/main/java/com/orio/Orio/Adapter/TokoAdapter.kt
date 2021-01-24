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
import com.orio.Orio.db.Toko
import com.orio.Orio.show_toko

class TokoAdapter (val mCtx: Context, val layoutResId: Int, val list: List<Toko> )
    : ArrayAdapter<Toko>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textAlamat = view.findViewById<TextView>(R.id.textAlamat)
        val textDeskripsi = view.findViewById<TextView>(R.id.textDeskripsi)
        val textLat = view.findViewById<TextView>(R.id.textLat)
        val textLong = view.findViewById<TextView>(R.id.textLong)
        val textUpdate = view.findViewById<ImageView>(R.id.TextUpdate)
        val textDelete = view.findViewById<ImageView>(R.id.TextDelete)
        val user = list[position]

        textNama.text = user.nama
        textAlamat.text = user.alamat
        textDeskripsi.text = user.deskripsi
        textLat.text = user.lat
        textLong.text = user.long

        textUpdate.setOnClickListener {
            showUpdateDialog(user)
        }
        textDelete.setOnClickListener {
            Deleteinfo(user)
        }

        return view

    }

    private fun Deleteinfo(toko: Toko) {
        val progressDialog = ProgressDialog(context,
            R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting…")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("toko")
        mydatabase.child(toko.id).removeValue()
        Toast.makeText(mCtx,"Deleted!!",Toast.LENGTH_SHORT).show()
        val intent = Intent(context, show_toko::class.java)
        context.startActivity(intent)
    }



    private fun showUpdateDialog(toko: Toko) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.activity_update_toko, null)

        val textnama = view.findViewById<EditText>(R.id.upNama)
        val textalamat = view.findViewById<EditText>(R.id.upAlamat)
        val textdeskripsi = view.findViewById<EditText>(R.id.upDeskripsi)
        val textlat = view.findViewById<EditText>(R.id.upLat)
        val textlong = view.findViewById<EditText>(R.id.upLong)

        textnama.setText(toko.nama)
        textalamat.setText(toko.alamat)
        textdeskripsi.setText(toko.deskripsi)
        textlat.setText(toko.lat)
        textlong.setText(toko.long)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbUsers = FirebaseDatabase.getInstance().getReference("toko")

            val nama = textnama.text.toString().trim()

            val alamat = textalamat.text.toString().trim()

            val deskripsi = textdeskripsi.text.toString().trim()

            val lat = textlat.text.toString().trim()

            val long = textlong.text.toString().trim()

            if (nama.isEmpty()){
                textnama.error = "isi kolom nama"
                textnama.requestFocus()
                return@setPositiveButton
            }

            if (alamat.isEmpty()){
                textalamat.error = "isi kolom alamat"
                textalamat.requestFocus()
                return@setPositiveButton
            }
            if (deskripsi.isEmpty()){
                textdeskripsi.error = "isi kolom deskripsi"
                textdeskripsi.requestFocus()
                return@setPositiveButton
            }
            if (lat.isEmpty()){
                textlat.error = "isi kolom latitude"
                textlat.requestFocus()
                return@setPositiveButton
            }
            if (long.isEmpty()){
                textlong.error = "isi kolom longitude"
                textlong.requestFocus()
                return@setPositiveButton
            }

            val toko = Toko(toko.id,nama,alamat,deskripsi,lat,long)

            dbUsers.child(toko.id).setValue(toko).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()
    }
}