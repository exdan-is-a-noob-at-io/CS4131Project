package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

object FirebaseUtil {
    private var DATABASE: DatabaseReference? = null
    private var STORAGE: FirebaseStorage? = null

    val database:DatabaseReference
        get() {
            if (DATABASE == null) DATABASE = Firebase.database.reference
            return DATABASE!!
        }

    val storage: FirebaseStorage
        get() {
            if (STORAGE == null) STORAGE = Firebase.storage
            return STORAGE!!
        }
}