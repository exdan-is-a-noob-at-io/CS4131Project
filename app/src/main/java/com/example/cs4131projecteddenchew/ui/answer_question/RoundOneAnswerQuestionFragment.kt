package com.example.cs4131projecteddenchew.ui.answer_question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.Comment
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import katex.hourglass.`in`.mathlib.MathView
import kotlinx.android.synthetic.main.fragment_view_comments.*

class RoundOneAnswerQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = RoundOneAnswerQuestionFragment()
    }

    private lateinit var viewModel: RoundOneAnswerQuestionViewModel
    lateinit var statementMathView: MathView
    lateinit var answerEditText:EditText
    lateinit var submitQuestionButton:Button
    lateinit var editTextAnswerStatus:EditText
    lateinit var explainationMathView:MathView
    lateinit var discussQuestionButton:Button
    lateinit var nextQuestionButton:Button
    lateinit var sourceTextViewRoundOne:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.round_one_answer_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoundOneAnswerQuestionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statementMathView = view.findViewById(R.id.statementMathView)
        answerEditText = view.findViewById(R.id.answerEditText)
        submitQuestionButton = view.findViewById(R.id.submitQuestionButton)
        editTextAnswerStatus = view.findViewById(R.id.editTextAnswerStatus)
        explainationMathView = view.findViewById(R.id.explainationMathView)
        discussQuestionButton = view.findViewById(R.id.discussQuestionButton)
        nextQuestionButton = view.findViewById(R.id.nextQuestionButton)
        sourceTextViewRoundOne = view.findViewById(R.id.sourceTextViewRoundOne)


        submitQuestionButton.setOnClickListener {
            loadAnswer()
        }
        nextQuestionButton.setOnClickListener {
            //make new qn

            /**var out:Long = 0
            if (RoundOneAnswerQuestionViewModel.idNotDone.size == 0){
                Toast.makeText(context, "Next Problem is Recycled!", Toast.LENGTH_LONG).show()
                out = RoundOneAnswerQuestionViewModel.allIDs.random()
            } else{
                out = RoundOneAnswerQuestionViewModel.idNotDone.random()
            }**/


            RoundOneAnswerQuestionViewModel.previousPost.qnType?.let { it1 -> FirebaseUtil.getNewQuestion(it1, requireContext()) }
        }

        discussQuestionButton.setOnClickListener {
            MainActivity.setLoadingVisible(true)
            FirebaseUtil.getComments(RoundOneAnswerQuestionViewModel.previousPost)
        }


        val resultObserverAttemptQuestion = Observer<Post>{
            result ->

            var minusOne:Long = -1
            var zero:Long = 0
            var four:Long = 4

            if (result.qnType == minusOne){

            }
            else if (result.qnType == four){

            } else{
                RoundOneAnswerQuestionViewModel.previousPost = RoundOneAnswerQuestionViewModel.selectedPost.value!!
                loadData()
            }


        }
        RoundOneAnswerQuestionViewModel.selectedPost.observe(viewLifecycleOwner, resultObserverAttemptQuestion)

        val resultObserverComments = Observer<ArrayList<Comment>>{
            result ->

            if (result.size == 0){
                MainActivity.setLoadingVisible(false)
                findNavController().navigate(R.id.action_navigation_round_one_answer_question_to_navigation_comments)
            }
            else if (result[0].comment.equals("")){

            }
            else{
                MainActivity.setLoadingVisible(false)
                findNavController().navigate(R.id.action_navigation_round_one_answer_question_to_navigation_comments)
            }

            Log.i("TAG", "part one works" + result.toString())


        }
        RoundOneAnswerQuestionViewModel.questionComments.observe(viewLifecycleOwner, resultObserverComments)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData(){
        Log.i("TAG", "Load DATA Called")
        var post = RoundOneAnswerQuestionViewModel.previousPost
        var zero:Long = 0
        if (post?.id?.equals(zero) == true){
            Log.i("TAG", "question not loaded sucessfully!")
            return
        }


        statementMathView.setDisplayText(post?.questionStatement)
        view?.resources?.let { editTextAnswerStatus.setTextColor(it.getColor(R.color.grey_20)) }
        answerEditText.setText("")
        editTextAnswerStatus.setText("")
        explainationMathView.setDisplayText("")
        sourceTextViewRoundOne.setText("Source: " + post.source + "\n" + "Question ID: " + post.id)

        RoundOneAnswerQuestionViewModel.selectedPost.value = RoundOneAnswerQuestionViewModel.defaultPost
    }

    fun loadAnswer(){
        var post = RoundOneAnswerQuestionViewModel.previousPost
        Log.i("TAG", answerEditText.text.toString())
        if (answerEditText.text.toString().equals(post?.answer)){
            editTextAnswerStatus.setText("You Are Correct!")
            view?.resources?.let { editTextAnswerStatus.setTextColor(it.getColor(R.color.Green_Apple)) }
        } else {
            editTextAnswerStatus.setText("Aw. Seems like you got that wrong")
            view?.resources?.let { editTextAnswerStatus.setTextColor(it.getColor(R.color.LoveRed)) }
        }

        explainationMathView.setDisplayText(post?.explaination)

        if (adminViewModel.user_data.value?.id != null){
            if (post != null) {
                FirebaseUtil.addQuestionDone(adminViewModel.user_data.value?.id!!, post.id!!, requireContext())
            }
        }

        post.id?.let { RoundOneAnswerQuestionViewModel.solvedQuestionsArrayList.add(it) }
        RoundOneAnswerQuestionViewModel.idNotDone.remove(post.id)

    }

}