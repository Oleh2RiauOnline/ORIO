package com.orio.Orio

import android.text.TextUtils
import android.view.View
import android.widget.Toast

register.setOnClickListener(object : View.OnClickListener {
    override fun onClick(v: View) {
        val txtUsername: String = username.getText().toString()
        val txtName: String = name.getText().toString()
        val txtEmail: String = email.getText().toString()
        val txtPassword: String = password.getText().toString()
        if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName)
            || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)
        ) {
            Toast.makeText(this@RegisterActivity, "Empty credentials!", Toast.LENGTH_SHORT).show()
        } else if (txtPassword.length < 6) {
            Toast.makeText(this@RegisterActivity, "Password too short!", Toast.LENGTH_SHORT).show()
        } else {
            registerUser(txtUsername, txtName, txtEmail, txtPassword)
        }
    }
})