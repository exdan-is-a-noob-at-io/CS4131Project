package com.example.cs4131projecteddenchew

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cs4131projecteddenchew.model.FirebaseUtil

class MainActivity : AppCompatActivity() {
    companion object Test{
        lateinit var progressBar:ProgressBar
        lateinit var window_:Window


        fun setLoadingVisible(boolean: Boolean){
            if (boolean){
                progressBar?.setVisibility(View.VISIBLE)
                window_.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            } else {
                progressBar.setVisibility(View.GONE);
                window_.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

    lateinit var appBarConfiguration:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_90))

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_profile))


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        progressBar = findViewById<ProgressBar>(R.id.loadingMain)
        window_ = window
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        FirebaseUtil.getImage()
    }

    override fun onResume() {
        super.onResume()
        FirebaseUtil.getImage()
    }

    override fun onRestart() {
        super.onRestart()
        FirebaseUtil.getImage()
    }
}