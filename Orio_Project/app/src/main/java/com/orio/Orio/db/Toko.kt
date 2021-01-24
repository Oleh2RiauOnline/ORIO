package com.orio.Orio.db

data class Toko(
    val id : String,
    val nama: String,
    val alamat : String,
    val deskripsi : String,
    val lat : String,
    val long : String
){
    constructor() : this("","","","","","")
}
