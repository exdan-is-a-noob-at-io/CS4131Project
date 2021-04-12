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
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.cs4131projecteddenchew.R
import com.example.cs4131projecteddenchew.model.FirebaseUtil
import com.example.cs4131projecteddenchew.model.RegexUtil
import com.example.cs4131projecteddenchew.model.RegexUtil.checkName
import com.example.cs4131projecteddenchew.model.User
import com.example.cs4131projecteddenchew.ui.splash.SplashActivity
import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.storage.internal.Util
import kotlinx.android.synthetic.main.fragment_onboarding_four_login.*
import kotlinx.android.synthetic.main.fragment_onboarding_four_sign_up.*
import kotlinx.android.synthetic.main.fragment_onboarding_four_sign_up.emailInputLayout
import androidx.lifecycle.Observer

class FragmentOnboardingFourSignUp : Fragment()  {

    lateinit var name: TextInputEditText
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var passwordAgain: TextInputEditText
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var viewModel:adminViewModel
    var name_: String = ""
    var email_:String = ""
    var password_: String = ""
    var passwordAgain_:String = ""

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

        viewModel = SplashActivity.viewModel


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
                ds.color = Color.parseColor("#DDDDDD")
                ds.isFakeBoldText = true
            }
        }
        ss.setSpan(clickableSpan, 15, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = ss
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = Color.TRANSPARENT

        //todo make the option for the pw hidden


        name.doAfterTextChanged {
            if (it?.toString()?.length == 0){
                nameInputLayout.error = null
            } else if (it?.toString()?.length!! < 3){
                nameInputLayout.error = "Name must be at least 3 characters long!"
            } else if (!RegexUtil.checkName(it.toString())){
                nameInputLayout.error = "Name may only consist of letters, numbers and underscores"
                //Log.d("TAG", it.toString())
            } else{
                nameInputLayout.error = null
            }
        }

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

        passwordAgain.doAfterTextChanged {
            if (it?.length == 0){
                passwordInputLayoutAgain.error = null
            }
            else if (!passwordAgain.text.toString()?.equals(password.text.toString())){
                passwordInputLayoutAgain.error = "Passwords do not match"
            } else{
                passwordInputLayoutAgain.error = null
            }
        }



        //on click
        button.setOnClickListener{
            //todo this
            name_ = name.text.toString()
            email_ = email.text.toString()
            password_ = password.text.toString()
            passwordAgain_ = passwordAgain.text.toString()
            //todo change this to snack bar?
            if (name_.isEmpty() || email_.isEmpty() || password_.isEmpty() || passwordAgain_.isEmpty()){
                Toast.makeText(context, "Some fields not filled in", Toast.LENGTH_LONG).show()
            } else if (name_.length < 3){
                Toast.makeText(context, "Name must be at least 3 characters long!", Toast.LENGTH_LONG).show()
            } else if (!RegexUtil.checkName(name_)){
                Toast.makeText(context, "Name may only consist of letters, numbers and underscores", Toast.LENGTH_LONG).show()
            } else if (!RegexUtil.checkEmail(email_)){
                Toast.makeText(context, "Invalid Email", Toast.LENGTH_LONG).show()
            }
            else if (passwordAgain_ == password_){
                FirebaseUtil.checkEmail(User.encryptVal(name_), User.encryptVal(email_), context)
            }
            else{
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            }

        }




        val resultObserver = Observer<String> {
                result ->
            if (result == "-1" || result == "" || result == "-2"){
            }
            else{
                viewModel.writeNewUser(User(adminViewModel.id.value, name_, email_, password_, false).userSafe)
                name.setText("")
                email.setText("")
                password.setText("")
                passwordAgain.setText("")
                Toast.makeText(context, "Account Created!", Toast.LENGTH_LONG).show()
            }

        }

        adminViewModel.id.observe(viewLifecycleOwner, resultObserver)

        return root
    }
}