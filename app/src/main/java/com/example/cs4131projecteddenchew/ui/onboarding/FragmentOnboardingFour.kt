package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cs4131projecteddenchew.R
import kotlinx.android.synthetic.main.activity_onboarding.*
import kotlinx.android.synthetic.main.fragment_onboarding_four.*

class FragmentOnboardingFour : Fragment() {
    companion object Fragment{
        var fragmentManager_:FragmentManager? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_four, container, false)

        fragmentManager_ = fragmentManager

        return root
    }

    override fun onStart() {
        super.onStart()
        val activity:OnboardingActivity = activity as OnboardingActivity
        activity.removeTabLayout()
    }

    override fun onResume() {
        super.onResume()
        val activity:OnboardingActivity = activity as OnboardingActivity
        activity.removeTabLayout()
    }
}