package com.example.cs4131projecteddenchew.ui.question_suggest

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.model.RegexUtil
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import kotlinx.android.synthetic.main.make_question_fragment.*
import java.util.*
import kotlin.math.min

class makeQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = makeQuestionFragment()
    }

    private lateinit var viewModel: MakeQuestionViewModel
    var spinnerAdapterTargetted: ArrayAdapter<String>? = null
    var spinnerAdapterPublishing: ArrayAdapter<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.make_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakeQuestionViewModel::class.java)

        spinnerAdapterTargetted=
            context?.let { ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item,
                viewModel.targettedLevel
            ) }
        spinnerAdapterTargetted?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTargettedLevel.setAdapter(spinnerAdapterTargetted)


        spinnerAdapterPublishing=
            context?.let { ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_item,
                viewModel.publishingStatus
            ) }
        spinnerAdapterPublishing?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPublishingStatus.setAdapter(spinnerAdapterPublishing)

        (activity as MainActivity?)?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)




        radioButtonRound1.setOnClickListener{
            onRadioButtonClicked(it)
        }

        radioButtonRound2.setOnClickListener{
            onRadioButtonClicked(it)
        }

        val resultObserver = Observer<Long> {
            result ->
            Log.i("TAG", "Observer calling is fucking you over")
            var zero:Long = 0
            if (result.equals(zero)){}
            else if (result > 0){
                qnIDEditText.setText(result.toString())
                var questionStatement:String = RegexUtil.addBackSlash(editTextQuestion.text.toString())

                if (!questionStatement.isEmpty()){
                    var source:String = sourceEditText.text.toString()
                    var qnType:Long = 0
                    var answer = ""

                    if (radioButtonRound2.isChecked){
                        qnType = 4
                        answer = "This is a Round 2 Question; no answer is available!"
                    }
                    else{
                        qnType = spinnerTargettedLevel.selectedItemPosition.toLong()
                        answer = answerEditText.text.toString()
                    }

                    var explaination:String = RegexUtil.addBackSlash(editTextExplaination.text.toString())
                    var published:Boolean = (spinnerPublishingStatus.selectedItemPosition == 1)
                    var comments = ArrayList<Long>()
                    var tags = getTags(editTextTags.text.toString())

                    var post:Post = Post(result, adminViewModel.user_data.value?.id, questionStatement, source, qnType, answer,
                            explaination, comments, tags, published)
                    Log.i("TAG", post.toString())
                    FirebaseUtil.writeNewQuestion(post, context)
                }
            }
            else{
                qnIDEditText.setText("-")
                Log.i("TAG", "Calling from observer")
            }
        }

        MakeQuestionViewModel.postId.observe(viewLifecycleOwner, resultObserver)

        saveQuestionButton.setOnClickListener {
            if (qnIDEditText.text.toString().equals("-") || qnIDEditText.text.toString().isEmpty()){
                MakeQuestionViewModel.postId.value = -1
                FirebaseUtil.getPostID()
            } else {
                var questionStatement:String = RegexUtil.addBackSlash(editTextQuestion.text.toString())
                var source:String = sourceEditText.text.toString()
                var qnType:Long = 0
                var answer = ""

                if (radioButtonRound2.isChecked){
                    qnType = 4
                }
                else{
                    qnType = spinnerTargettedLevel.selectedItemPosition.toLong()
                    answer = answerEditText.text.toString()
                }

                var explaination:String = RegexUtil.addBackSlash(editTextExplaination.text.toString())
                var published:Boolean = (spinnerPublishingStatus.selectedItemPosition == 1)
                var comments = ArrayList<Long>()
                var tags = getTags(editTextTags.text.toString())

                var post:Post = Post(qnIDEditText.text.toString().toLong(), adminViewModel.user_data.value?.id, questionStatement,
                        source, qnType, answer, explaination, comments, tags, published)
                Log.i("TAG", post.toString())
                FirebaseUtil.writeNewQuestion(post, context)
            }
        }

        /* todo use some snackbar to tell the user when the post upload works */
    }

    public fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioButtonRound1 ->
                    if (checked) {
                        spinnerTargettedLevel.isEnabled = true
                        answerEditText.isEnabled = true
                        spinnerTargettedLevel.setAlpha(1.0f)
                    }
                R.id.radioButtonRound2 ->
                    if (checked) {
                        spinnerTargettedLevel.isEnabled = false
                        answerEditText.isEnabled = false
                        spinnerTargettedLevel.setAlpha(0.4f)
                    }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onStartResume()
    }

    override fun onStart() {
        super.onStart()
        onStartResume()
    }

    fun resetAll(){
        editTextQuestion.setText("")
        sourceEditText.setText("")
        answerEditText.setText("")
        editTextExplaination.setText("")
        editTextTags.setText("")
    }


    //todo make the radio buttons work
    fun onStartResume(){
        Log.i("TAG", "onStartResume Called!")
        var zero:Long = 0
        var minusOne:Long = -1
        var four:Long = 4
        if (MakeQuestionViewModel.selectedPost.value?.id == zero){

        } else if (MakeQuestionViewModel.selectedPost.value?.id == minusOne){
            qnIDEditText.setText("-")
            MakeQuestionViewModel.selectedPost.value = MakeQuestionViewModel.defaultPost
        }
        else{
            var currPost = MakeQuestionViewModel.selectedPost.value
            Log.i("TAG", currPost.toString())
            editTextQuestion.setText(currPost?.questionStatement)
            sourceEditText.setText(currPost?.source)
            if (currPost?.qnType?.equals(four) == true){
                radioButtonRound2.isSelected = true
                spinnerTargettedLevel.isEnabled = false
                answerEditText.isEnabled = false
                spinnerTargettedLevel.setAlpha(0.4f)
            } else{
                radioButtonRound1.isSelected = true
                spinnerTargettedLevel.isEnabled = true
                answerEditText.isEnabled = true
                spinnerTargettedLevel.setAlpha(1.0f)

                currPost?.qnType?.let { spinnerTargettedLevel.setSelection(it.toInt()) }
                answerEditText.setText(currPost?.answer)
            }

            editTextExplaination.setText(currPost?.explaination)
            editTextTags.setText(currPost?.tags?.let { getTags(it) })
            qnIDEditText.setText(currPost?.id.toString())
            MakeQuestionViewModel.selectedPost.value = MakeQuestionViewModel.defaultPost
        }
    }

    fun getTags(tags:List<String>): String{
        var out:String = ""
        for (i in 0 until tags.size){
            if (i != 0){
                out += ", "
            }
            out += tags[i]
        }
        return out
    }

    fun getTags(string:String):ArrayList<String> {
        var out:ArrayList<String> = ArrayList()


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