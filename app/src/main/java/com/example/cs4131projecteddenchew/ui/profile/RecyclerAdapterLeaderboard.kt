package com.example.cs4131projecteddenchew.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.MainActivity
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.database.DatabaseViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList


class RecyclerAdapterLeaderboard(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapterLeaderboard.ViewHolder>() {
    private val names = ArrayList<String>()
    private val exp = ArrayList<Long>()


    init {
        updateData()
    }


    fun updateData(){
        LeaderboardViewModel.leaderboard.sort()
        LeaderboardViewModel.leaderboard.forEach{
            User.decryptVal(it.name)?.let { it1 -> names.add(it1) }
            it.exp?.let { it1 -> exp.add(it1) }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_layout_leaderboard,
                parent,
                false
            )
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namePosition.text = (position+1).toString() + ": " + names[position]
        holder.expPosition.text = exp[position].toString() + "EXP"
    }

    override fun getItemCount(): Int {
        return names.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namePosition: TextView
        var expPosition:TextView

        init {
            namePosition = itemView.findViewById(R.id.namePosition)
            expPosition = itemView.findViewById(R.id.expPosition)
        }
    }
}