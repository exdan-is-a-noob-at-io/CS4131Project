package com.example.cs4131projecteddenchew.ui.database

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import katex.hourglass.`in`.mathlib.MathView
import kotlin.collections.ArrayList

class RecyclerAdapterDatabaseDatabase(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterDatabaseDatabase.ViewHolder>() {
    private val names = ArrayList<String>()

    private val descriptions = ArrayList<String>()

    init {

        var tagedQuestions = DatabaseViewModel.tagedQuestions.value

        tagedQuestions?.forEach { post->
            names.add(post?.questionStatement!!)
            post?.tags?.let { getTags(it) }?.let { descriptions.add(it) }
            //Log.i("TAG", post.toString())
        }

    }

    fun getTags(tags:List<String>): String{
        var out:String = ""
        for (i in 0 until tags.size){
            if (i != 0){
                out += ", "
            }
            out += tags[i]
        }
        return out
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout_database, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.statementTextView.setDisplayText(names[position])
        holder.answerTextView.text = descriptions[position]
        holder.textviewstatement.text = "Statement:"
        holder.textViewTags.text = "Tags:"
    }

    override fun getItemCount(): Int {
        return names.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var statementTextView: MathView
        var answerTextView: TextView
        var textviewstatement:TextView
        var textViewTags:TextView

        init {
            statementTextView = itemView.findViewById(R.id.statementTextView)
            answerTextView = itemView.findViewById(R.id.answerTextView)
            textviewstatement = itemView.findViewById(R.id.textviewstatement)
            textViewTags = itemView.findViewById(R.id.textViewTags)


            itemView.setOnClickListener { view ->
                val position = adapterPosition
                if (position == 0){
                    DatabaseViewModel.selectedPost.value = DatabaseViewModel.defaultPost
                    DatabaseViewModel.selectedPost.value = DatabaseViewModel.newPost
                }
                else{
                    DatabaseViewModel.selectedPost.value = DatabaseViewModel.defaultPost
                    DatabaseViewModel.selectedPost.value = DatabaseViewModel.tagedQuestions.value?.get(position - 1)
                }
            }
        }
    }
}