package com.example.cs4131projecteddenchew.model

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cs4131projecteddenchew.ui.home.HomeViewModel
import com.example.cs4131projecteddenchew.ui.onboarding.FragmentOnboardingFourLogin
import com.example.cs4131projecteddenchew.ui.question_suggest.MakeQuestionViewModel
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
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


    /** creating posts **/

    fun writeNewQuestion(post:Post, context: Context?){
        try {
            val database = FirebaseUtil.database
            database.child("posts").child(post.id.toString()).setValue(post).addOnSuccessListener {
                Log.e("TAG", "post Upload works!!!")


                //todo dont make this a hash map

                var questionIDSet = ArrayList<Long>()
                database.child("users").child(post.posterId!!).child("questionsPosted").get().addOnSuccessListener {
                    it.children.forEach{
                        if (!questionIDSet.contains(it.value as Long)){
                            questionIDSet.add(it.value as Long)
                        }
                    }
                    post.id?.let { it1 -> questionIDSet.add(it1) }

                    database.child("users").child(post.posterId!!).child("questionsPosted").setValue(questionIDSet)


                    Toast.makeText(context, "Question Details Updated!", Toast.LENGTH_LONG).show()
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

                            //todo fix this agh
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
}