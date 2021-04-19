package com.example.cs4131projecteddenchew.ui.answer_question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.model.User
import java.io.File

class RoundOneAnswerQuestionViewModel : ViewModel() {
    companion object Test{
        val defaultPost = Post(0, "", "", "", -1, "", "", ArrayList<Long>(), ArrayList<String>(), false)
        var selectedPost = MutableLiveData<Post> ( defaultPost )
        var previousPost = defaultPost

        var solvedQuestionsArrayList = ArrayList<Long>()
        var idNotDone = ArrayList<Long>()
        var allIDs = ArrayList<Long>()
    }

}