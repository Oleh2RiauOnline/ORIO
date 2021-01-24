package com.orio.user

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class Register : AppCompatActivity() {
    var fullName: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    var phone: EditText? = null
    var registerBtn: Button? = null
    var goToLogin: Button? = null
    var valid = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        fullName = findViewById(R.id.registerName)
        email = findViewById(R.id.registerEmail)
        password = findViewById(R.id.registerPassword)
        phone = findViewById(R.id.registerPhone)
        registerBtn = findViewById(R.id.registerBtn)
        goToLogin = findViewById(R.id.gotoLogin)
        checkField(fullName)
        checkField(email)
        checkField(password)
        checkField(phone)
    }

    fun checkField(textField: EditText?): Boolean {
        if (textField!!.text.toString().isEmpty()) {
            textField.error = "Error"
            valid = false
        } else {
            valid = true
        }
        return valid
    }
}