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
        private var user: User? = User("", "", -1, "-1", "", "", "", ArrayList<String>())
        lateinit var filesDir:File
        lateinit var fileToUse: File
        var id = MutableLiveData("-2")
        var user_data = MutableLiveData(User("", "", -1, "-1", "", "", "", ArrayList<String>()))
    }



    var name_ = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")

    fun initFiles(filesDir_: File){
        filesDir = filesDir_
        fileToUse = File(filesDir, "accountDeatils.txt")
    }


    //for debugging
    fun writeToFile(string:String){
        var output: PrintWriter? = null
        try {
            output = PrintWriter(fileToUse)
            output.print(string)
            output.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun writeToFile(){
        var output: PrintWriter? = null
        try {
            output = PrintWriter(fileToUse)
            output.print(user_data.value?.id)
            Log.i("TAG", "the id is: " + user_data.value?.id)
            output.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun writeNewUser(userSafe: UserSafe?) {
        val database = FirebaseUtil.database

        //testing
        //database.child("users").child("name").setValue("hello world!").addOnSuccessListener {
        //    Log.e("TAG", "Works")
        //}.addOnFailureListener{
        //    Log.e("TAG", "fails :(", it)
        //}

        Log.e("TAG", "In viewmodel")

        if (userSafe?.id == "-2" || userSafe?.id == "-1"){
            return
        }
        else{
            userSafe?.id?.let { database.child("users").child(userSafe.id!!).setValue(userSafe).addOnSuccessListener {
                Log.e("TAG", "WORKS")
            }.addOnFailureListener {
                Log.e("TAG", "write new user fails", it)
            } }
        }



    }


    fun checkUser(){
        try {
            val scanner: Scanner = Scanner(fileToUse)
            var id = scanner.next()
            scanner.close()

            Log.i("TAG", "THE ID IS " + id)
            if (id == "-1" || id == ""){
                user = User("", "", -1, "-2", "", "", "", ArrayList<String>())
                user_data.value = user
                return
            }

            //use it for the value
            val database = FirebaseUtil.database
            database.child("users").child(id).get().addOnSuccessListener {
                Log.i("TAG", "Got value ${it.value}")

                user = it.getValue(User::class.java)
                if (user == null){
                    user = User("", "", -1, "-2", "", "", "", ArrayList<String>())
                    user_data.value = user
                }
                else{
                    user_data.value = user
                }

            }.addOnFailureListener{
                Log.e("TAG", "Error getting data", it)
                user = User("", "", -1, "-2", "", "", "", ArrayList<String>())
                user_data.value = user
            }
        } catch (e: ParseException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()
            user = User("", "", -1, "-2", "", "", "", ArrayList<String>())
            user_data.value = user

        } catch (e: FileNotFoundException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()
            user = User("", "", -1, "-2", "", "", "", ArrayList<String>())
            user_data.value = user
        } catch (e: NoSuchElementException) {
            var output = PrintWriter(fileToUse)
            output.print("")
            output.close()
            user = User("", "", -1, "-2", "", "", "", ArrayList<String>())
            user_data.value = user
        }

    }
}