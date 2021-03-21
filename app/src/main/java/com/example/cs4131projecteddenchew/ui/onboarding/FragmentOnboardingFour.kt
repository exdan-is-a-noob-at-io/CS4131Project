package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.cs4131projecteddenchew.R

class FragmentOnboardingFour : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_four, container, false)
        val cardview = root.findViewById<CardView>(R.id.card_view_onboarding_four)

        val viewHolder = ViewHolderOnboarding(cardview)

        viewHolder.text.setText("This is just for testing; frag 4")
        viewHolder.imageView.setBackgroundResource(R.drawable.venti_image_temp)

        return root
    }
}