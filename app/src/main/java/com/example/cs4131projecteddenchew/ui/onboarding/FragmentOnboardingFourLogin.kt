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
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.RegexUtil
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_onboarding_four_sign_up.*

class FragmentOnboardingFourLogin : Fragment()  {

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var button: Button
    private lateinit var textView: TextView


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_onboarding_four_login, container, false)

        email = root.findViewById(R.id.emailInput)
        password = root.findViewById(R.id.passwordInput)
        button = root.findViewById(R.id.onboardingButton)
        textView = root.findViewById(R.id.onboardingTextView)

        val ss = SpannableString("No account yet? Sign Up")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val navController = FragmentOnboardingFour.navController

                Log.d("TAG", FragmentOnboardingFour.navController.toString())
                navController?.navigate(R.id.login_to_sign_up)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = Color.BLACK
                ds.isFakeBoldText = true
            }
        }
        ss.setSpan(clickableSpan, 16, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = ss
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT

        email.doAfterTextChanged {
            if (it?.length == 0){
                emailInputLayout.error = null
            }
            else if (!RegexUtil.checkEmail(it.toString())){
                emailInputLayout.error = "Invalid Email!"
            } else{
                emailInputLayout.error = null
            }
        }

        //on click
        button.setOnClickListener{
            //todo this
        }

        return root
    }
    /**
    override fun onStart() {
        super.onStart()
        val activity:OnboardingActivity = activity as OnboardingActivity
        activity.removeTabLayout()
    }

    override fun onResume() {
        super.onResume()
        val activity:OnboardingActivity = activity as OnboardingActivity
        activity.removeTabLayout()
    }**/
}