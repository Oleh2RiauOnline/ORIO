package com.orio.Orio.db

data class Merchant(
    val id : String,
    val email: String,
    val password : String
){
    constructor() : this("","","")
}
