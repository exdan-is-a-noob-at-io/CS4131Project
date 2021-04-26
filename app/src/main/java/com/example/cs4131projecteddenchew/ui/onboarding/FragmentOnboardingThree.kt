package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import kotlinx.android.synthetic.main.activity_onboarding.*

class FragmentOnboardingThree : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_three, container, false)
        val cardview = root.findViewById<CardView>(R.id.card_view_onboarding_three)

        val viewHolder = ViewHolderOnboarding(cardview)

        viewHolder.text.setText("Upload Questions!")
        viewHolder.imageView.setBackgroundResource(R.drawable.post_image)

        return root
    }

    override fun onStart() {
        super.onStart()
        val activity:OnboardingActivity = activity as OnboardingActivity
        activity.showTabLayout()
    }

    override fun onResume() {
        super.onResume()
        val activity:OnboardingActivity = activity as OnboardingActivity
        activity.showTabLayout()
    }
}