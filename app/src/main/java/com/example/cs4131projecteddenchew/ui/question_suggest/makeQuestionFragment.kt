package com.example.cs4131projecteddenchew.ui.question_suggest

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import kotlinx.android.synthetic.main.make_question_fragment.*
import java.util.*

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
        // TODO: Use the ViewModel

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
                    }
                R.id.radioButtonRound2 ->
                    if (checked) {
                        spinnerTargettedLevel.isEnabled = false
                        answerEditText.isEnabled = false
                    }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                //todo why the hell this is not working
                //todo where the hell is the toolbar
                findNavController().navigate(R.id.action_navigation_make_question_to_navigation_home)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}