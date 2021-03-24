package com.example.cs4131projecteddenchew.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.model.UserSafe
import com.example.cs4131projecteddenchew.ui.onboarding.OnboardingActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000
    private var user: User = User(-1, "", "", "", -1, , "", , "", ArrayList<String>())

    var fileToUse: File = File(filesDir, "accountDeatils.txt")

    var id: Long? = null
    var profileSrc: String? = null
    var name: String? = null
    var bio: String? = null
    var exp: Long? = null
    var email: String? = null
    var pw: String? = null
    var friends: List<String>? = null

    val data = hashMapOf(
            "id" to id,
            "profileSrc" to profileSrc,
            "name" to name,
            "bio" to bio,
            "exp" to exp,
            "email" to email,
            "pw" to pw,
            "friends" to friends
    )

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

    fun checkUser(){
        try {
            val scanner: Scanner = Scanner(fileToUse)
            var id = scanner.next()
            scanner.close()

            //use it for the value
            val database = FirebaseUtil.database
            database.child("users").child(id).get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                //user = it.getValue(User.class)
                //todo return thing

            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }
        } catch (e: ParseException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()

        } catch (e: FileNotFoundException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()
        }

    }
}