package com.example.cs4131projecteddenchew.ui.profile

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.GamificationUtil
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.onboarding.OnboardingActivity
import com.example.cs4131projecteddenchew.ui.splash.SplashActivity
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel.Test.filesDir
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var notificationsViewModel: ProfileViewModel
    var imageInfoFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageInfoFile = File(filesDir, "profileImageURI.txt")

        imageView.setOnClickListener(ImageClickListener(this))


        signOutButton.setOnClickListener {
            SplashActivity.viewModel.writeToFile(""); //resets account details storage
            adminViewModel.user_data.value = User("", "", -1, "-2", "", "", "", ArrayList<String>())
            adminViewModel.id.value = (-2).toString()

            ProfileViewModel.exp = 0

            startActivityForResult(Intent(activity, OnboardingActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }, 0)
        }
    }

    override fun onStart() {
        super.onStart()
        onStartResume()
    }

    override fun onResume() {
        super.onResume()
        onStartResume()
    }

    fun onStartResume(){
        loadImage()

        var zero:Long = 0
        if (ProfileViewModel.exp == zero){
            ProfileViewModel.exp = adminViewModel.user_data.value?.exp!!
        }

        var exp = ProfileViewModel.exp
        var currentEXP = GamificationUtil.getExp(exp)
        var levelEXP = GamificationUtil.getLevelExp(exp)
        var level = GamificationUtil.getLevel(exp)

        profileNameTextView.setText(User.decryptVal(adminViewModel.user_data.value?.name))
        textViewLevel.setText("Level " + level.toString())
        textViewEXP.setText("EXP: " + currentEXP.toString() + "/" + levelEXP.toString())


        var progressStatus = 0
        var progressToMoveTo = 100000*currentEXP/levelEXP



        val handler = Handler()
        Thread {
            Thread.sleep(500)
            handler.post(Runnable {
                ObjectAnimator.ofInt(expProgressBar, "progress", progressToMoveTo.toInt()).start();
            })
        }.start()
    }

    private fun readData(): String {
        if (!imageInfoFile!!.exists()) {
            return ""
        }
        val scanner = Scanner(imageInfoFile)
        val string = StringBuilder(scanner.nextLine())

        while (scanner.hasNextLine())
            string.append("\n" + scanner.nextLine())


        scanner.close()
        return string.toString()
    }

    private fun loadImage() {
        val string: String = readData()
        if (string.isNotEmpty()) {
            imageView!!.setImageURI(Uri.parse(readData()))
        } else {
            imageView!!.setImageResource(R.drawable.face_trans)
        }

    }
}