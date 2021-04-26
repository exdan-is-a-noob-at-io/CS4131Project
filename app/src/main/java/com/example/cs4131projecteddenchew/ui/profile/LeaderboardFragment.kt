package com.example.cs4131projecteddenchew.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.ui.home.RecyclerAdapterDatabase
import com.example.cs4131projecteddenchew.ui.home.RecyclerAdapterDifficulty
import com.example.cs4131projecteddenchew.ui.home.RecyclerAdapterQuestion

class LeaderboardFragment : Fragment() {

    companion object {
        fun newInstance() = LeaderboardFragment()
    }

    private lateinit var viewModel: LeaderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.leaderboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LeaderboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recycler_view_leaderboard:RecyclerView = view.findViewById(R.id.recycler_view_leaderboard)
        var layoutManagerLeaderboard = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view_leaderboard.setLayoutManager(layoutManagerLeaderboard)

        var adapter = context?.let { RecyclerAdapterLeaderboard(it) }

        recycler_view_leaderboard.setAdapter(adapter)
    }

}