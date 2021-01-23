package com.orio.Orio

data class CustomerAdapter(
    val id : String,
    val email : String,
    val password : String
){
    constructor() : this("","","")
}
