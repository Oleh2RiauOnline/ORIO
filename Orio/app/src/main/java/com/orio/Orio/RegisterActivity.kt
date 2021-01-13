package com.orio.Orio

import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuthException

class RegisterActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var konfpassword : EditText
    private lateinit var register : Button

    private lateinit var firebaseAuthException: FirebaseAuthException

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById (R.id.email)
        password = findViewById (R.id.password)
        konfpassword = findViewById (R.id.konfpassword)
        register = findViewById (R.id.register)

        firebaseAuthException = firebaseAuthException.instance()

        register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val txtEmail = email.text.toString()
                val txtPassword = password.text.toString()
                val txtKonfPassword = konfpassword.getText().toString()

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)
                    || TextUtils.isEmpty(txtKonfPassword)
                ) {
                    Toast.makeText(this@RegisterActivity, "Empty credentials!", Toast.LENGTH_SHORT)
                        .show()
                } else if (txtPassword.length < 6) {
                    Toast.makeText(this@RegisterActivity, "Password too short!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    registerUser(txtEmail, txtPassword, txtKonfPassword)
                }
            }
        })
    }

    private fun registerUser(email: String, Password: String, KonfPassword: String) {
    firebaseAuthException.createUserWithEmailAndPassword(email, password, konfpassword).addOnCompleteListener<BiometricPrompt.AuthenticationResult
    }
}