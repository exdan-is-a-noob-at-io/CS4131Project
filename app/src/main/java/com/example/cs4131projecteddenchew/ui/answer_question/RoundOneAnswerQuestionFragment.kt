package com.example.cs4131projecteddenchew.ui.answer_question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs4131projecteddenchew.R

class RoundOneAnswerQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = RoundOneAnswerQuestionFragment()
    }

    private lateinit var viewModel: RoundOneAnswerQuestionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.round_one_answer_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoundOneAnswerQuestionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}