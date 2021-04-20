package com.example.cs4131projecteddenchew.ui.answer_question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4131projecteddenchew.model.Comment
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.model.User
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class RoundOneAnswerQuestionViewModel : ViewModel() {
    companion object Test{
        val defaultPost = Post(0, "", "", "", -1, "", "", ArrayList<Long>(), ArrayList<String>(), false)
        var selectedPost = MutableLiveData<Post> ( defaultPost )
        var previousPost = defaultPost

        var commentsID = MutableLiveData<Long> ( -1 )
        val defaultComment = Comment(-1, "", "-1", Date())

        var questionComments = MutableLiveData<ArrayList<Comment>>(ArrayList<Comment>(Arrays.asList(defaultComment)))

        var solvedQuestionsArrayList = ArrayList<Long>()
        var idNotDone = ArrayList<Long>()
        var allIDs = ArrayList<Long>()
    }

}