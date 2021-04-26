package com.example.cs4131projecteddenchew.ui.answer_question

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.Comment
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import katex.hourglass.`in`.mathlib.MathView
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoundTwoAnswerQuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoundTwoAnswerQuestionFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: RoundOneAnswerQuestionViewModel
    lateinit var statementMathView: MathView
    lateinit var submitQuestionButton: Button
    lateinit var explainationMathView: MathView
    lateinit var discussQuestionButton: Button
    lateinit var nextQuestionButton: Button
    lateinit var sourceTextViewRoundTwo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_round_two_answer_question, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoundTwoAnswerQuestionFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RoundTwoAnswerQuestionFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statementMathView = view.findViewById(R.id.statementMathView)
        submitQuestionButton = view.findViewById(R.id.submitQuestionButton)
        explainationMathView = view.findViewById(R.id.explainationMathView)
        discussQuestionButton = view.findViewById(R.id.discussQuestionButton)
        nextQuestionButton = view.findViewById(R.id.nextQuestionButton)
        sourceTextViewRoundTwo = view.findViewById(R.id.sourceTextViewRoundTwo)


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
                RoundOneAnswerQuestionViewModel.previousPost = RoundOneAnswerQuestionViewModel.selectedPost.value!!
                loadData()
            } else{

            }


        }
        RoundOneAnswerQuestionViewModel.selectedPost.observe(viewLifecycleOwner, resultObserverAttemptQuestion)

        val resultObserverComments = Observer<ArrayList<Comment>>{
                result ->

            if (result.size == 0){
                MainActivity.setLoadingVisible(false)
                findNavController().navigate(R.id.action_navigation_round_two_answer_question_to_navigation_comments)
            }
            else if (result[0].comment.equals("")){

            }
            else{
                MainActivity.setLoadingVisible(false)
                findNavController().navigate(R.id.action_navigation_round_two_answer_question_to_navigation_comments)
            }

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
        explainationMathView.setDisplayText("")
        sourceTextViewRoundTwo.setText("Source: " + post.source + "\n" + "Question ID: " + post.id)

        RoundOneAnswerQuestionViewModel.selectedPost.value = RoundOneAnswerQuestionViewModel.defaultPost
    }

    fun loadAnswer(){
        var post = RoundOneAnswerQuestionViewModel.previousPost

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