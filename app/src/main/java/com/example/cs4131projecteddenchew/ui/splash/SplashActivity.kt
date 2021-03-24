package com.example.cs4131projecteddenchew.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.model.UserSafe
import com.example.cs4131projecteddenchew.ui.onboarding.OnboardingActivity
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000

    private lateinit var viewModel: adminViewModel

    //todo splash screen design
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //todo account data storage

        viewModel = ViewModelProvider(this).get(adminViewModel::class.java)

        /**val userObserver = Observer<User>{newUser ->
            if (newUser.){
                startActivity(Intent(applicationContext, OnboardingActivity::class.java))
            }
            else{
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }**/


    }
}