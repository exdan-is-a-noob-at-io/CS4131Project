package com.example.cs4131projecteddenchew.ui.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4131projecteddenchew.model.Post
import java.util.*
import kotlin.collections.ArrayList

class DatabaseViewModel : ViewModel() {

    companion object Test{
        val defaultPost = Post(0, "", "", "", 0, "", "", ArrayList<Long>(), ArrayList<String>(), false)
        val newPost = Post(-1, "", "", "", 0, "", "", ArrayList<Long>(), ArrayList<String>(), false)
        var selectedPost = MutableLiveData<Post> ( defaultPost )
        val bufferPostedQuestions = ArrayList<Post>(Arrays.asList(defaultPost))
        val zeroPostedQuestions = ArrayList<Post>(ArrayList<Post>())
        var tagedQuestions = MutableLiveData<ArrayList<Post>>(zeroPostedQuestions)
        var callInSignal = MutableLiveData<Int>(0)
    }
}