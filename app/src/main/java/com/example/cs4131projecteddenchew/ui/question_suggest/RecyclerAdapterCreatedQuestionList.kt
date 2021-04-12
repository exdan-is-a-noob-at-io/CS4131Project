package com.example.cs4131projecteddenchew.ui.question_suggest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import katex.hourglass.`in`.mathlib.MathView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapterCreatedQuestionList(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterCreatedQuestionList.ViewHolder>() {
    //todo change these.
    private val names = ArrayList<String>()

    private val descriptions = ArrayList<String>()

    init {
        //pull data from db
        names.add("Add Question! $\\pi \\neq 3.14$")
        descriptions.add("")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout_edit_question, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.statementTextView.setDisplayText(names[position])
        holder.answerTextView.text = descriptions[position]
        holder.textviewstatement.text = "Statement:"
        holder.textView5.text = "Answer:"
    }

    override fun getItemCount(): Int {
        return names.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var statementTextView: MathView
        var answerTextView: TextView
        var textviewstatement:TextView
        var textView5:TextView

        init {
            statementTextView = itemView.findViewById(R.id.statementTextView)
            answerTextView = itemView.findViewById(R.id.answerTextView)
            textviewstatement = itemView.findViewById(R.id.textviewstatement)
            textView5 = itemView.findViewById(R.id.textView5)


            itemView.setOnClickListener { view ->
                val position = adapterPosition
            }
        }
    }
}