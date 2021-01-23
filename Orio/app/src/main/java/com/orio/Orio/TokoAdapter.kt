package com.orio.Orio

data class TokoAdapter(
    val id : String,
    val nama : String,
    val alamat : String,
    val deskripsi : String,
    val lat : String,
    val long : String
){
    constructor() : this("","","", "", "", "")
}
