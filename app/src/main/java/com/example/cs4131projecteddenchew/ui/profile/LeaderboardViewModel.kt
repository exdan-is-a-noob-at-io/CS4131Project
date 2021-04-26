package com.example.cs4131projecteddenchew.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs4131projecteddenchew.model.User

class LeaderboardViewModel : ViewModel() {
    companion object Test{
        var updateStatus = MutableLiveData(0)
        var leaderboard = ArrayList<User>()
    }
}