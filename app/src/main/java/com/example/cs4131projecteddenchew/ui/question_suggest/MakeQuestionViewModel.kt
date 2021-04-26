package com.example.cs4131projecteddenchew.ui.question_suggest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import java.util.*
import kotlin.collections.ArrayList

class MakeQuestionViewModel : ViewModel() {

    companion object Test{
        var postId:MutableLiveData<Long> = MutableLiveData(0)
        val bufferPostedQuestions = ArrayList<Long>(Arrays.asList(-1))
        var postedQuestions = MutableLiveData<ArrayList<Long>>(ArrayList<Long>(Arrays.asList(-1)))
        var postedQuestionsPosts = MutableLiveData<ArrayList<Post>>(ArrayList<Post>())
        val defaultPost = Post(0, "", "", "", 0, "", "", ArrayList<Long>(), ArrayList<String>(), false)
        val newPost = Post(-1, "", "", "", 0, "", "", ArrayList<Long>(), ArrayList<String>(), false)
        var selectedPost = MutableLiveData<Post> ( defaultPost )
    }

    var targettedLevel: ArrayList<String> = ArrayList<String>(Arrays.asList("Junior", "Senior", "Open", "Others"))
    var publishingStatus: ArrayList<String> = ArrayList<String>(Arrays.asList("Work In Progress; Private", "Public"))




}