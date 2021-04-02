package com.example.cs4131projecteddenchew.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.model.UserSafe
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

class adminViewModel(): ViewModel() {
    companion object Test{
        private var user: User? = User("-1", "", "", "", -1, "", "", ArrayList<String>())
        lateinit var filesDir:File
        lateinit var fileToUse: File
        var id:Long = -1
    }

    var user_data = MutableLiveData( User("-1", "", "", "", -1, "", "", ArrayList<String>()) )

    var name_ = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")

    fun initFiles(filesDir_:File){
        filesDir = filesDir_
        fileToUse = File(filesDir, "accountDeatils.txt")
    }

    fun writeNewUser(userSafe: UserSafe) {
        val database = FirebaseUtil.database
        userSafe.id?.let { database.child("users").child(it).setValue(userSafe) }
    }


    fun checkUser(){
        try {
            val scanner: Scanner = Scanner(fileToUse)
            var id = scanner.next()
            scanner.close()

            //use it for the value
            val database = FirebaseUtil.database
            database.child("users").child(id).get().addOnSuccessListener {
                Log.i("TAG", "Got value ${it.value}")

                user = it.getValue(User::class.java)
                //todo return thing
                if (user == null){
                    user = User("-2", "", "", "", -1, "", "", ArrayList())
                    user_data.value = user
                }
                else{
                    user_data.value = user
                }

            }.addOnFailureListener{
                Log.e("TAG", "Error getting data", it)
                user = User("-2", "", "", "", -1, "", "", ArrayList())
                user_data.value = user
            }
        } catch (e: ParseException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()
            user = User("-2", "", "", "", -1, "", "", ArrayList())
            user_data.value = user

        } catch (e: FileNotFoundException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()
            user = User("-2", "", "", "", -1, "", "", ArrayList())
            user_data.value = user
        }

    }
}