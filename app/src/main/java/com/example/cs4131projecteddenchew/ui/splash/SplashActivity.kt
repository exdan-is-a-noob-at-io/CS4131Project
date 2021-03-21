package com.example.cs4131projecteddenchew.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.ui.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000

    //todo splash screen design
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //todo account data storgae

        if (true){
            startActivity(Intent(applicationContext, OnboardingActivity::class.java))
        }
        else{
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}