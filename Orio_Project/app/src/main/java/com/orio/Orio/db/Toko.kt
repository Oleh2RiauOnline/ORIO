package com.orio.Orio.db

data class Toko(
    val id : String,
    val nama: String,
    val alamat : String
){
    constructor() : this("","","")
}
