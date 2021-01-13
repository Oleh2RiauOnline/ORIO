package com.orio.Orio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import android.view.View

class MainActivity : AppCompatActivity() {
private lateinit var register : Button
private lateinit var login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register = findViewById (R.id.register)
        login = findViewById (R.id.login)

        register.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                startActivity(object intent(MainActivity.this, RegisterActivity.class))
        })
    }
}
}