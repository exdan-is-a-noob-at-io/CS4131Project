package com.example.cs4131projecteddenchew.model

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cs4131projecteddenchew.ui.onboarding.FragmentOnboardingFourLogin
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


object FirebaseUtil {
    private var DATABASE: DatabaseReference? = null
    private var STORAGE: FirebaseStorage? = null

    val database:DatabaseReference
        get() {
            if (DATABASE == null) DATABASE = Firebase.database.reference // getReferenceFromUrl("https://praeclarum-default-rtdb.firebaseio.com/")     //database.reference
            return DATABASE!!
        }

    val storage: FirebaseStorage
        get() {
            if (STORAGE == null) STORAGE = Firebase.storage
            return STORAGE!!
        }

    fun checkEmail(emailEncrypted:String?, context: Context?){
        database.child("users").get().addOnSuccessListener {
            var unique = true
            it.children.forEach{
                if (it.child("email").value?.equals(emailEncrypted) == true){
                    unique = false
                }
            }
            if (unique){
                getID()
            }
            else{
                Toast.makeText(context, "Email Already In Use!", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener{
            Log.e("TAG", "Error getting data getID", it)
        }
    }

    fun checkLogin(emailEncrypted:String?, pwEncrypted:String?, context: Context?){
        database.child("users").get().addOnSuccessListener {
            var correct = false
            it.children.forEach{
                if (it.child("email").value?.equals(emailEncrypted) == true && it.child("pw").value?.equals(pwEncrypted) == true){
                    adminViewModel.user_data.value = it.getValue(User::class.java)
                    correct = true
                }
            }
            if (correct){
                //log in
                Log.i("TAG", "Details Correct!")
            }
            else{
                Toast.makeText(context, "Details Incorrect!", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener{
            Log.e("TAG", "Error getting data getID", it)
        }
    }

    fun getID(){
        database.child("users").get().addOnSuccessListener {
            addTrailingZeros((it.childrenCount).toString())
        }.addOnFailureListener{
            Log.e("TAG", "Error getting data getID", it)
        }
    }

    fun addTrailingZeros(id_:String){
        var out = id_
        while (out.length < 6){
            out = "0" + out
        }
        adminViewModel.id.value = out
    }
}