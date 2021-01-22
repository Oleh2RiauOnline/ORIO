package com.example.orio_baru

data class Customer(
    val id : String,
    val email: String,
    val password : String
){
    constructor() : this("","","")
}
