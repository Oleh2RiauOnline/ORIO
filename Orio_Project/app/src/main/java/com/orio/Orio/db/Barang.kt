package com.orio.Orio.db

data class Barang(
    val id : String,
    val nama: String,
    val kuantitas : String,
    val harga : String
){
    constructor() : this("","","","")
}
