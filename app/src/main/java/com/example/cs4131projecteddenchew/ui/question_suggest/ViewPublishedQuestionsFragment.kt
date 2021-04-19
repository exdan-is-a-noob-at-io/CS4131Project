package com.example.cs4131projecteddenchew.ui.question_suggest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.Post
import com.example.cs4131projecteddenchew.ui.home.RecyclerAdapterDifficulty
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import kotlinx.android.synthetic.main.make_question_fragment.*
import java.util.ArrayList
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPublishedQuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPublishedQuestionsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recyclerViewQuestions: RecyclerView
    lateinit var layoutManagerQuestions: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_published_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewQuestions = view.findViewById(R.id.recycler_view_posted_questions)
        layoutManagerQuestions = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewQuestions.setLayoutManager(layoutManagerQuestions)

        var adapterQuestions = context?.let { RecyclerAdapterCreatedQuestionList(it) }
        recyclerViewQuestions.setAdapter(adapterQuestions)


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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewPublishedQuestionsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewPublishedQuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}