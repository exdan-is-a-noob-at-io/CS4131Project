package com.example.cs4131projecteddenchew.ui.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.cs4131projecteddenchew.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_onboarding_four_sign_up.*

class FragmentOnboardingFourSignUp : Fragment()  {

    private lateinit var name: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var passwordAgain: TextInputEditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_four_sign_up, container, false)

        name = root.findViewById(R.id.nameInput)
        email = root.findViewById(R.id.emailInput)
        password = root.findViewById(R.id.passwordInput)
        passwordAgain = root.findViewById(R.id.passwordInputAgain)
        button = root.findViewById(R.id.onboardingButton)
        textView = root.findViewById(R.id.onboardingTextView)


        val ss = SpannableString("Existing User? Log in")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val navController = FragmentOnboardingFour.navController

                Log.d("TAG", navController.toString())
                navController?.navigate(R.id.sign_up_to_login)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = Color.BLACK
                ds.isFakeBoldText = true
            }
        }
        ss.setSpan(clickableSpan, 15, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = ss
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT

        return root
    }
}