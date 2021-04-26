package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.cs4131projecteddenchew.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class FragmentOnboardingTwo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_two, container, false)
        val cardview = root.findViewById<CardView>(R.id.card_view_onboarding_two)

        val viewHolder = ViewHolderOnboarding(cardview)

        viewHolder.text.setText("Solve Questions!")
        viewHolder.imageView.setBackgroundResource(R.drawable.solve_math_problems)

        return root
    }
}