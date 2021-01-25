package com.orio.Orio.db

data class Pesanan(
    val id : String,
    val tanggal: String,
    val barang : String
){
    constructor() : this("","","")
}
