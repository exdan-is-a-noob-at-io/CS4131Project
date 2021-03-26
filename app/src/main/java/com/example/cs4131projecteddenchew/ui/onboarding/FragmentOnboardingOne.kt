package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.cs4131projecteddenchew.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class FragmentOnboardingOne : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_one, container, false)
        val cardview = root.findViewById<CardView>(R.id.card_view_onboarding_one)

        val viewHolder = ViewHolderOnboarding(cardview)

        viewHolder.text.setText("This is just for testing; frag 1")
        viewHolder.imageView.setBackgroundResource(R.drawable.venti_image_temp)

        return root
    }

    override fun onStart() {
        super.onStart()
        tabLayoutIndicator.setVisibility(View.VISIBLE)
    }

    override fun onResume() {
        super.onResume()
        tabLayoutIndicator.setVisibility(View.VISIBLE)
    }
}