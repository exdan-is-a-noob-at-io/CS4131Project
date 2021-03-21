package com.example.cs4131projecteddenchew.ui.onboarding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.google.android.material.snackbar.Snackbar


class ViewHolderOnboarding(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageView: ImageView
    var text: TextView

    init {
        imageView = itemView.findViewById(R.id.imageView)
        text = itemView.findViewById(R.id.topText)
    }
}
