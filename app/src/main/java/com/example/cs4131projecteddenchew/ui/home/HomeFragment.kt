package com.example.cs4131projecteddenchew.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.ui.onboarding.FragmentOnboardingFour
import com.example.cs4131projecteddenchew.ui.question_suggest.MakeQuestionViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var recyclerViewDifficulty: RecyclerView
    lateinit var recyclerViewQuestion: RecyclerView
    lateinit var recyclerViewDatabase: RecyclerView
    lateinit var layoutManagerDifficulty: LinearLayoutManager
    lateinit var layoutManagerQuestion: LinearLayoutManager
    lateinit var layoutManagerDatabase: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewQuestion = view.findViewById(R.id.recycler_view_question)
        recyclerViewDifficulty = view.findViewById(R.id.recycler_view_difficulty)
        recyclerViewDatabase = view.findViewById(R.id.recycler_view_database)

        //horizontal is this line
        layoutManagerDifficulty = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerQuestion = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerDatabase = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewQuestion.setLayoutManager(layoutManagerQuestion)
        recyclerViewDifficulty.setLayoutManager(layoutManagerDifficulty)
        recyclerViewDatabase.setLayoutManager(layoutManagerDatabase)

        var adapterDifficulty = context?.let { RecyclerAdapterDifficulty(it) }
        var adapterQuestion = context?.let { RecyclerAdapterQuestion(it) }
        var adapterDatabase = context?.let { RecyclerAdapterDatabase(it) }

        recyclerViewDifficulty.setAdapter(adapterDifficulty)
        recyclerViewQuestion.setAdapter(adapterQuestion)
        recyclerViewDatabase.setAdapter(adapterDatabase)
    }

    override fun onStart() {
        super.onStart()
        //todo implement this
        val resultObserver = Observer< ArrayList<Long> >{
            result ->
            Log.i("TAG", result.toString())

            //so -1 cannot be typecasted as a LONG so I have to do this
            var minusOne:Long = -1

            if (result.size == 0){ }
            else if (result[0] != minusOne){
                val navController = view?.let { Navigation.findNavController(it) }
                navController?.navigate(R.id.action_navigation_home_to_navigation_viewQuestions)
            }
        }
        MakeQuestionViewModel.postedQuestions.observe(viewLifecycleOwner, resultObserver)
    }
}