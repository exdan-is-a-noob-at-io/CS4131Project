package com.example.cs4131projecteddenchew.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.ui.database.DatabaseViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList


class RecyclerAdapterDatabase(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterDatabase.ViewHolder>() {
    private val names = ArrayList<String>(
        Arrays.asList(
            "View All Qns!"
        )
    )


    private val images = ArrayList<Int>(
        Arrays.asList(
            R.drawable.document
        )
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.square_card_layout_tint,
                parent,
                false
            )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.sectionTypeTextView.text = names[position]
        holder.imageView.setBackgroundResource(images[position])
    }

    override fun getItemCount(): Int {
        return names.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionTypeTextView: TextView
        var imageView: ImageView

        init {
            sectionTypeTextView = itemView.findViewById(R.id.sectionTypeTextView)
            imageView = itemView.findViewById(R.id.cardIconImageView)

            itemView.setOnClickListener { view ->
                MainActivity.setLoadingVisible(true)
                val position = adapterPosition
                DatabaseViewModel.callInSignal.value = 0
                DatabaseViewModel.callInSignal.value = 1
            }
        }
    }
}