package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class FirebaseUtil {
    companion object Test {
        lateinit var database: DatabaseReference
        lateinit var storage: FirebaseStorage
    }

    init {
        database = Firebase.database.reference
        storage = Firebase.storage
    }


}