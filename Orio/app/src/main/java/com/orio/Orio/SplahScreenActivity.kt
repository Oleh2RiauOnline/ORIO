package com.orio.Orio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orio.Orio.Customer.Login
import com.orio.Orio.Merchant.LoginMerchant
import java.lang.Exception

class SplahScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var background = object : Thread() {
            override fun run() {
                try{
                    Thread.sleep(3000)

                    val intent = Intent(baseContext, Login::class.java)
                    startActivity(intent)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}