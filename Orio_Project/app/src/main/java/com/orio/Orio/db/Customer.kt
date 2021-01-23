package com.orio.Orio.db

data class Customer(
    val id : String,
    val email: String,
    val password : String
){
    constructor() : this("","","")
}
