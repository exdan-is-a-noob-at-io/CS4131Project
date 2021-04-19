package com.example.cs4131projecteddenchew.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapterDifficulty(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterDifficulty.ViewHolder>() {
    private val names = ArrayList<String>(Arrays.asList(
        "Junior (R1)",
        "Senior (R1)",
        "Open (R1)",
        "Others (R1)",
        "Round 2"
    ))

    //todo change images
    private val images = ArrayList<Int>(Arrays.asList(
        R.drawable.basic_math_icon,
        R.drawable.intermediate_math_icon,
        R.drawable.advanced_math_icon_alt,
        R.drawable.miscellaneous_math_icon,
        R.drawable.pen_on_paper
    ))


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
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
                val position = adapterPosition
                Log.i("TAG", "QUestion type is: " + position.toString())
                FirebaseUtil.getNewQuestion(position.toLong(), context)
            }
        }
    }
}