package com.orio.Orio

data class Customer(
    val id : String,
    val nama : String,
    val nohp : String
){
    constructor(): this("","","")
}