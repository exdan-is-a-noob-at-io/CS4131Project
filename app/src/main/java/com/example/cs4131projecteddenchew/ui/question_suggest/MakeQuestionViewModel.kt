package com.example.cs4131projecteddenchew.ui.question_suggest

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MakeQuestionViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var targettedLevel: ArrayList<String> = ArrayList<String>(Arrays.asList("Junior", "Senior", "Open", "Others"))
    var publishingStatus: ArrayList<String> = ArrayList<String>(Arrays.asList("Published", "Unreleased"))

}