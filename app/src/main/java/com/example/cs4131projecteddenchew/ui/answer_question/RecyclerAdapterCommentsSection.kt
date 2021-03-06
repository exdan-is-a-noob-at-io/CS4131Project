package com.example.cs4131projecteddenchew.ui.answer_question

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.Comment
import katex.hourglass.`in`.mathlib.MathView
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapterCommentsSection(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterCommentsSection.ViewHolder>() {


    private val names = ArrayList<String>()

    private val descriptions = ArrayList<String>()

    private val time = ArrayList<String>()

    init {

        var postedComments = RoundOneAnswerQuestionViewModel.questionComments.value

        postedComments?.forEach { comment->
            names.add(0, comment.commenter!!)
            descriptions.add(0, comment.comment!!)
            time.add(0, comment.timeString!!)
        }


        RoundOneAnswerQuestionViewModel.questionComments.value = ArrayList<Comment>(Arrays.asList(RoundOneAnswerQuestionViewModel.defaultComment))

    }


    fun addObject(comment:Comment){
        names.add(0, comment.commenter!!)
        descriptions.add(0, comment.comment!!)
        time.add(0, comment.timeString!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_layout_comments, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.TextViewPostedBy.text = names[position] + " says:"
        holder.commentTextView.text = descriptions[position]
        holder.TextViewTime.text = "posted on " + time[position]
    }

    override fun getItemCount(): Int {
        return names.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var TextViewPostedBy:TextView
        var commentTextView:TextView
        var TextViewTime:TextView

        init {
            TextViewPostedBy = itemView.findViewById(R.id.TextViewPostedBy)
            commentTextView = itemView.findViewById(R.id.commentTextView)
            TextViewTime = itemView.findViewById(R.id.textViewTime)
        }
    }
}