package com.example.cs4131projecteddenchew.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.onboarding.OnboardingActivity
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.text.ParseException
import java.util.*


class SplashActivity : AppCompatActivity() {
    companion object Test{
        lateinit var viewModel: adminViewModel
    }

    private val SPLASH_TIME_OUT = 3000

    //todo splash screen design
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProvider(this).get(adminViewModel::class.java)
        viewModel.initFiles(filesDir)

        viewModel.checkUser()



        val resultObserver = Observer<User> {
                result ->
            if (result.id.equals("-1")){
                Log.i("TAG", "onboarding via splash " + result.id)
            }
            else if (result.id.equals("-2")){
                startActivity(Intent(applicationContext, OnboardingActivity::class.java))
            }
            else{
                //viewModel.writeToFile(""); resets account details storage
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }


            //todo change it so you cant go back; as per greenpass
        }

        adminViewModel.user_data.observe(this, resultObserver)

        /**val userObserver = Observer<User>{newUser ->
            if (newUser.){
                startActivity(Intent(applicationContext, OnboardingActivity::class.java))
            }
            else{
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }**/


    }

    fun checkUser(): Long {
        try {
            val scanner: Scanner = Scanner(adminViewModel.fileToUse)
            var id = scanner.next()
            scanner.close()

            if (id.toLong().equals(0)){
                return -1
            }

            return id.toLong()

        } catch (e: ParseException) {
            var output = PrintWriter(adminViewModel.fileToUse)
            output.print("-1")
            output.close()
            return -1

        } catch (e: FileNotFoundException) {
            var output = PrintWriter(adminViewModel.fileToUse)
            output.print("-1")
            output.close()
            return -1
        }
    }
}