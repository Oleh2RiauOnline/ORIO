package com.orio.Orio

data class Customer(
    val id : String,
    val email: String,
    val password : String
){
    constructor() : this("","","")
}
