package com.example.cs4131projecteddenchew.model

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cs4131projecteddenchew.ui.answer_question.RoundOneAnswerQuestionViewModel
import com.example.cs4131projecteddenchew.ui.database.DatabaseViewModel
import com.example.cs4131projecteddenchew.ui.home.HomeViewModel
import com.example.cs4131projecteddenchew.ui.onboarding.FragmentOnboardingFourLogin
import com.example.cs4131projecteddenchew.ui.profile.ProfileViewModel
import com.example.cs4131projecteddenchew.ui.question_suggest.MakeQuestionViewModel
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.lang.Exception
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList


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


    fun uploadImage(photoUri: Uri) {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${adminViewModel.user_data.value?.id}.jpg")

        val uploadTask = imageRef.putFile(photoUri)
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            // nothing to be implemented
        }.addOnSuccessListener {
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }



    /**login**/
    fun checkEmail(nameEncrypted:String?, emailEncrypted:String?, context: Context?){
        database.child("users").get().addOnSuccessListener {
            var unique = true
            it.children.forEach{
                if (it.child("email").value?.equals(emailEncrypted) == true){
                    unique = false
                }
            }
            if (unique){
                checkName(nameEncrypted, context)
            }
            else{
                Toast.makeText(context, "Email Already In Use!", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener{
            Log.e("TAG", "Error getting data getID", it)
        }
    }

    fun checkName(nameEncrypted:String?, context: Context?){
        database.child("users").get().addOnSuccessListener {
            var unique = true
            it.children.forEach{
                if (it.child("name").value?.equals(nameEncrypted) == true){
                    unique = false
                }
            }
            if (unique){
                getID()
            }
            else{
                Toast.makeText(context, "Name Already In Use!", Toast.LENGTH_LONG).show()
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

    fun addPoints(points:Long, userID:String){
        try {
            val database = FirebaseUtil.database
            var questionIDSet = ArrayList<Long>()
            database.child("users").child(userID).child("exp").get().addOnSuccessListener {
                var newExp:Long = it.value.toString().toLong() + points
                database.child("users").child(userID).child("exp").setValue(newExp)
                ProfileViewModel.exp = newExp
            }.addOnFailureListener {
                Log.e("TAG", "write new post fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }


    //comments

    fun addComment(comment: Comment, post:Post, context:Context, userID:String){
        try {
            val database = FirebaseUtil.database
            var commentsID = ArrayList<Long>()
            database.child("posts").child(post.id.toString()).child("comments").get().addOnSuccessListener {
                it.children.forEach{
                    if (!commentsID.contains(it.value as Long)){
                        commentsID.add(it.value as Long)
                    }
                }
                if (!commentsID.contains(comment.id)){
                    commentsID.add(comment.id!!)
                }

                database.child("posts").child(post.id.toString()).child("comments").setValue(commentsID)
                database.child("comments").child(comment.id.toString()).setValue(comment)
                RoundOneAnswerQuestionViewModel.commentsID.value = -1
                /**Gamification**/
                addPoints(2, post.posterId!!)
                addPoints(2, userID)

                Log.i("TAG", comment.toString())

            }.addOnFailureListener {
                Log.e("TAG", "write new post fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }


    /** creating posts **/

    fun addQuestionDone(userID:String, postID:Long, context:Context){
        try {
            val database = FirebaseUtil.database
            var questionIDSet = ArrayList<Long>()
            database.child("users").child(userID).child("questionsDone").get().addOnSuccessListener {
                it.children.forEach{
                    if (!questionIDSet.contains(it.value as Long)){
                        questionIDSet.add(it.value as Long)
                    }
                }
                if (!questionIDSet.contains(postID)){
                    questionIDSet.add(postID)
                    /**Gamification**/
                    addPoints(300, userID)
                }
                else{
                    /**Gamification**/
                    addPoints(26, userID)
                }

                database.child("users").child(userID).child("questionsDone").setValue(questionIDSet)

            }.addOnFailureListener {
                Log.e("TAG", "write new post fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }

    fun getNewQuestion(qnType:Long, context:Context){
        try {
            val database = FirebaseUtil.database
            database.child("posts").get().addOnSuccessListener { allPosts->

                var solvedQuestionsArrayList = ArrayList<Long>()
                var idNotDone = ArrayList<Long>()
                var allIDs = ArrayList<Long>()
                var minusOne:Long = -1

                if (adminViewModel.user_data.value?.id != null){
                    database.child("users").child(adminViewModel.user_data.value?.id!!).child("questionsDone").get()
                            .addOnSuccessListener { solvedQuestions ->
                        solvedQuestions.children.forEach{
                            solvedQuestionsArrayList.add(it.getValue(Long::class.java)!!)
                        }
                        allPosts.children.forEach{
                            Log.i("TAG", it.value.toString())
                            Log.i("TAG", it.child("published").value.toString())
                            Log.i("TAG", it.child("qnType").value.toString())
                            Log.i("TAG", (it.child("published").value == true).toString())
                            Log.i("TAG", (it.child("qnType").value!!.equals(qnType)).toString())
                            if (it.child("published").value == true &&
                                    it.child("qnType").value!!.equals(qnType)){

                                Log.i("TAG", "INTO IF LOOP\n\n\n\n")
                                allIDs.add(it.child("id").value as Long)
                                if (!solvedQuestionsArrayList.contains(it.child("id").value)) {
                                    idNotDone.add(it.child("id").value as Long)
                                }
                            }
                        }

                        Log.i("TAG", idNotDone.toString())
                        Log.i("TAG", allIDs.toString())

                        var out:Long = 0

                        if (idNotDone.size == 0){
                            if (allIDs.size != 0){
                                Toast.makeText(context, "Next Problem is Recycled!", Toast.LENGTH_LONG).show()
                                out = allIDs.random()
                            }
                        } else{
                            out = idNotDone.random()
                        }


                        allPosts.children.forEach{
                            if (it.child("id").value?.equals(out) == true){
                                RoundOneAnswerQuestionViewModel.selectedPost.value = RoundOneAnswerQuestionViewModel.defaultPost
                                RoundOneAnswerQuestionViewModel.selectedPost.value = it.getValue(Post::class.java)
                                RoundOneAnswerQuestionViewModel.allIDs = allIDs
                                RoundOneAnswerQuestionViewModel.idNotDone = idNotDone
                                RoundOneAnswerQuestionViewModel.solvedQuestionsArrayList = solvedQuestionsArrayList
                            }
                        }

                    }.addOnFailureListener {
                        Log.e("TAG", "write new post fails", it)
                    }
                }

            }.addOnFailureListener {
                Log.e("TAG", "write new post fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }

    fun writeNewQuestion(post:Post, context: Context?){
        try {
            val database = FirebaseUtil.database
            database.child("posts").child(post.id.toString()).setValue(post).addOnSuccessListener {
                Log.e("TAG", "post Upload works!!!")

                var questionIDSet = ArrayList<Long>()
                database.child("users").child(post.posterId!!).child("questionsPosted").get().addOnSuccessListener {
                    it.children.forEach{
                        if (!questionIDSet.contains(it.value as Long)){
                            questionIDSet.add(it.value as Long)
                        }
                    }
                    if (!questionIDSet.contains(post.id)){
                        post.id?.let { it1 -> questionIDSet.add(it1) }
                        /**Gamification**/
                        addPoints(50, post.posterId!!)
                    }

                    database.child("users").child(post.posterId!!).child("questionsPosted").setValue(questionIDSet)


                    Toast.makeText(context, "Question Details Updated!", Toast.LENGTH_LONG).show()
                    MakeQuestionViewModel.postId.value = 0
                }.addOnFailureListener {
                    Log.e("TAG", "write new post fails", it)
                }




            }.addOnFailureListener {
                Log.e("TAG", "write new post fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }

    fun getQuestionsFromUser(user:User?){
        try {
            var postIDs = ArrayList<Long>()
            var posts = ArrayList<Post>()
            database.child("users").child(user?.id!!).child("questionsPosted").get().addOnSuccessListener {
                database.child("posts").get().addOnSuccessListener{ postsRoot ->
                    it.children.forEach{
                        if (!postIDs.contains(it.value as Long)){
                            postIDs.add(it.value as Long)

                            //seems to work now yayy
                            Log.i("TAG", postsRoot.child(it.value.toString()).toString())
                            var currentPost = postsRoot.child(it.value.toString()).getValue(Post::class.java)
                            if (currentPost != null) {
                                posts.add(currentPost)
                            }

                            Log.e("TAG", it.value.toString() + "is a valid qn")
                        }
                    }
                    MakeQuestionViewModel.postedQuestionsPosts.value = posts
                    MakeQuestionViewModel.postedQuestions.value = MakeQuestionViewModel.bufferPostedQuestions
                    MakeQuestionViewModel.postedQuestions.value = postIDs
                }.addOnFailureListener {
                    Log.e("TAG", "get Questions info fails", it)
                }

            }.addOnFailureListener {
                Log.e("TAG", "get Questions info fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "", it)
        }
    }

    fun getPostID(){
        database.child("posts").get().addOnSuccessListener {
            MakeQuestionViewModel.postId.value = (it.childrenCount).toString().toLong()
        }.addOnFailureListener{

        }
    }

    fun getCommentID(){
        database.child("comments").get().addOnSuccessListener {
            RoundOneAnswerQuestionViewModel.commentsID.value = (it.childrenCount).toString().toLong()
        }.addOnFailureListener{

        }
    }

    fun getComments(post: Post){
        try {
            val database = FirebaseUtil.database
            var commentsID = ArrayList<Long>()
            var comments = ArrayList<Comment>()
            database.child("posts").child(post.id.toString()).child("comments").get().addOnSuccessListener {
                it.children.forEach{
                    if (!commentsID.contains(it.value as Long)){
                        commentsID.add(it.value as Long)
                    }
                }

                FirebaseUtil.database.child("comments").get().addOnSuccessListener{ commentsList ->
                    commentsID.forEach{ commentID->
                        var currentPost = commentsList.child(commentID.toString()).getValue(Comment::class.java)
                        if (currentPost != null) {
                            comments.add(currentPost)
                        }
                    }


                    RoundOneAnswerQuestionViewModel.questionComments.value = comments
                }.addOnFailureListener {
                    Log.e("TAG", "get Questions info fails", it)
                }


            }.addOnFailureListener {
                Log.e("TAG", "write new post fails", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }

    fun getQuestionFromTags(tags:String){
        try {
            val database = FirebaseUtil.database
            database.child("posts").get().addOnSuccessListener { allPosts->


                var taggedQuestionsIDArrayList = ArrayList<Long>()
                var taggedQuestionsArrayList = ArrayList<Post>()

                var tagsArrayList = getTags(tags)

                allPosts.children.forEach{
                    if ((it.child("published").value == true)){
                        var noEqual = 0
                        var tagsInPost = it.child("tags").getValue<List<String>>()
                        tagsArrayList.forEach(){ tag->
                            if (tagsInPost != null) {
                                tagsInPost.forEach{ tag_ ->

                                    //todo fuzzy wuzzy?
                                    if (tag_.trim().equals(tag.trim(), ignoreCase = true)){
                                        noEqual += 1
                                    }
                                }
                            }

                            //Log.i("TAG", it.getValue(Post::class.java).toString() + noEqual.toString())
                        }


                        if (noEqual.equals(tagsArrayList.size)){
                            it.getValue(Post::class.java)?.let { it1 -> taggedQuestionsArrayList.add(it1) }
                        }
                    }

                }

                DatabaseViewModel.tagedQuestions.value = DatabaseViewModel.bufferPostedQuestions
                DatabaseViewModel.tagedQuestions.value = taggedQuestionsArrayList

            }.addOnFailureListener {
                Log.e("TAG", "Error", it)
            }
        }
        catch (it:Exception){
            Log.e("TAG", "exception goes brr brr", it)
        }
    }

    fun getTags(string:String): java.util.ArrayList<String> {
        var out: java.util.ArrayList<String> = java.util.ArrayList()


        Log.i("TAG", string)

        var inString = string


        var scanner = Scanner(inString)
        scanner.useDelimiter(",")
        while (scanner.hasNext()){
            var tag = scanner.next()
            Log.i("TAG", tag)
            if (!out.contains(tag.trim())  &&  !tag.isBlank()){
                out.add(tag.trim())
            }
        }
        scanner.close()

        Log.i("TAG", out.toString())

        return out
    }
}