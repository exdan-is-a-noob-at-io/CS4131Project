package com.example.cs4131projecteddenchew.ui.database

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.ui.question_suggest.MakeQuestionViewModel

class DatabaseFragment : Fragment() {

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
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewQuestions = view.findViewById(R.id.recycler_view_database)
        layoutManagerQuestions = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewQuestions.setLayoutManager(layoutManagerQuestions)

        var adapterQuestions = context?.let { RecyclerAdapterDatabaseDatabase(it) }
        recyclerViewQuestions.setAdapter(adapterQuestions)


        //todo fix this
        val resultObserver = Observer<Post> {
                result ->
            var one:Long = 1
            var minusOne:Long = -1
            Log.i("TAG", "From observer" + result.id.toString() + result.toString())
            if (result.id!! >= 1){
                findNavController().navigate(R.id.action_navigation_viewQuestions_to_navigation_make_question)
            }
            else if (result.id!!.equals(minusOne)){
                findNavController().navigate(R.id.action_navigation_viewQuestions_to_navigation_make_question)
            }
        }

        MakeQuestionViewModel.selectedPost.observe(viewLifecycleOwner, resultObserver)
    }




}