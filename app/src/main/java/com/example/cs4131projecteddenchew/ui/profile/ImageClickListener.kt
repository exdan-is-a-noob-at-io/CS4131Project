package com.example.cs4131projecteddenchew.ui.profile

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cs4131projecteddenchew.ui.profile.ImageDetailsActivity

class ImageClickListener(val context: Context?) : View.OnClickListener {
    constructor(fragment: Fragment) : this(fragment.context)

    override fun onClick(v: View?) {
        Intent(context, ImageDetailsActivity::class.java).also {
            context?.startActivity(it)
        }
    }
}