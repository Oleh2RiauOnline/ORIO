package com.orio.user

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class Login : AppCompatActivity() {
    var email: EditText? = null
    var password: EditText? = null
    var loginBtn: Button? = null
    var gotoRegister: Button? = null
    var valid = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById(R.id.loginPassword)
        password = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.loginBtn)
        gotoRegister = findViewById(R.id.gotoRegister)
        checkField(email)
        checkField(password)
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