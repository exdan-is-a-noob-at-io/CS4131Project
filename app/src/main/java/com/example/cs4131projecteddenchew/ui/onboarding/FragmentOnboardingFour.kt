package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.cs4131projecteddenchew.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class FragmentOnboardingFour : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_four, container, false)

        return root
    }

    override fun onStart() {
        super.onStart()
        tabLayoutIndicator.setVisibility(View.GONE)
    }

    override fun onResume() {
        super.onResume()
        tabLayoutIndicator.setVisibility(View.GONE)
    }
}