package com.example.cs4131projecteddenchew.ui.question_suggest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs4131projecteddenchew.R

class makeQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = makeQuestionFragment()
    }

    private lateinit var viewModel: MakeQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.make_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakeQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}