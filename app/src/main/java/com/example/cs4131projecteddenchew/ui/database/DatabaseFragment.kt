package com.example.cs4131projecteddenchew.ui.database

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.ui.answer_question.RoundOneAnswerQuestionViewModel
import com.example.cs4131projecteddenchew.ui.question_suggest.MakeQuestionViewModel
import kotlinx.android.synthetic.main.database_fragment.*


class DatabaseFragment : Fragment(){

    companion object {
        fun newInstance() = DatabaseFragment()
    }

    private lateinit var viewModel: DatabaseViewModel

    lateinit var recyclerViewQuestions: RecyclerView
    lateinit var layoutManagerQuestions: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.database_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewQuestions = view.findViewById(R.id.recycler_view_database)
        layoutManagerQuestions = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewQuestions.setLayoutManager(layoutManagerQuestions)

        var adapterQuestions = context?.let { RecyclerAdapterDatabaseDatabase(it) }
        recyclerViewQuestions.setAdapter(adapterQuestions)

        searchButton.setOnClickListener {
            FirebaseUtil.getQuestionFromTags(editTextTags.text.toString())
            MainActivity.setLoadingVisible(true)
        }


        val resultObserver = Observer<ArrayList<Post>> { result ->
            var one:Long = 1
            var minusOne:Long = -1
            var zero:Long = 0
            if (result.size == 0){
                RecyclerAdapterDatabaseDatabase.loadData()
            }
            else if (result[0].id != zero){
                RecyclerAdapterDatabaseDatabase.loadData()
                Log.i("TAG", "this is calling from database fragment\n\n\n\n")
            }

            adapterQuestions?.notifyDataSetChanged()
        }

        DatabaseViewModel.tagedQuestions.observe(viewLifecycleOwner, resultObserver)


        val resultObserverAttemptQuestion = Observer<Post>{
                result ->
            Log.i("TAG", result.toString())

            var minusOne:Long = -1
            var zero:Long = 0
            var four:Long = 4
            val navController = view?.let { Navigation.findNavController(it) }

            if (result.qnType == minusOne || RoundOneAnswerQuestionViewModel.previousPost.id != zero){

            }
            else if (result.qnType == four){
                RoundOneAnswerQuestionViewModel.previousPost = RoundOneAnswerQuestionViewModel.selectedPost.value!!
                navController?.navigate(R.id.action_navigation_database_to_navigation_round_two_answer_question)
            } else{
                RoundOneAnswerQuestionViewModel.previousPost = RoundOneAnswerQuestionViewModel.selectedPost.value!!
                navController?.navigate(R.id.action_navigation_database_to_navigation_round_one_answer_question)
            }


        }
        RoundOneAnswerQuestionViewModel.selectedPost.observe(viewLifecycleOwner, resultObserverAttemptQuestion)
    }

    override fun onStart() {
        super.onStart()
        onStartResume()
    }

    override fun onResume() {
        super.onResume()
        onStartResume()
    }

    fun onStartResume(){
        RoundOneAnswerQuestionViewModel.previousPost = RoundOneAnswerQuestionViewModel.defaultPost
        DatabaseViewModel.tagedQuestions.value = DatabaseViewModel.zeroPostedQuestions
    }


}