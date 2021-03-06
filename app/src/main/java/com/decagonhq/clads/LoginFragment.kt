package com.decagonhq.clads

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding // view binding for this current fragment (Login fragment)

    // Creating variables to store views references
    private lateinit var showPasswordIcon: ImageView
    private lateinit var hidePasswordIcon: ImageView
    private lateinit var password: EditText
    private lateinit var email: EditText
    private lateinit var signInWithGoogleBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var signUpForFreeLink: TextView
    private lateinit var forgotPasswordLink: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initializing the view binding variable
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        // Storing reference to views into variables
        showPasswordIcon = binding.loginScreenFragmentShowPasswordIcon
        hidePasswordIcon = binding.loginScreenFragmentHidePasswordIcon
        email = binding.loginScreenFragmentEmailAddressTextView
        password = binding.loginScreenFragmentPasswordTextView
        // Buttons
        signInWithGoogleBtn = binding.linearLayout3
        loginBtn = binding.loginScreenFragmentLoginButtonTextView
        // Text Links
        signUpForFreeLink = binding.loginScreenFragmentSignUpForFreeLinkTextView
        forgotPasswordLink = binding.loginScreenFragmentForgotPasswordTextView

        // Controlling the visibility of the password at the click of the visibility icons
        showPasswordIcon.setOnClickListener() {
            password.inputType = 1
            showPasswordIcon.visibility = View.GONE
            hidePasswordIcon.visibility = View.VISIBLE
        }

        hidePasswordIcon.setOnClickListener() {
            password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            hidePasswordIcon.visibility = View.GONE
            showPasswordIcon.visibility = View.VISIBLE
        }

        // Navigate to other screens at the buttons
        loginBtn.setOnClickListener() {
            when {
                !LoginFragmentValidation.emailValidator(email.text.toString()) -> {
                    email.error = "Invalid Email"
                }
                !LoginFragmentValidation.passwordValidator(password.text.toString()) -> {
                    password.error = "requires 6 characters or more"
                }
                else -> {
                    /*
                    Please take not that the navigation below will only work if the validation is passed
                    however, i am only validating if the email is valid and if the password is 6 characters or more
                    NOTE: This validation, LoginFragment.kt Class, should be updated later to include checking if the user
                    is a registered user or not
                     */
                    findNavController().navigate(R.id.action_login_fragment_to_dashboard_fragment)
                }
            }
        }

        // Move to forgot password page at the click to the forgot password text
        forgotPasswordLink.setOnClickListener() {
            findNavController().navigate(R.id.action_login_fragment_to_forgot_Password_fragment)
        }

        // Move to Sign up for free at the click of th Sign up for free text
        signUpForFreeLink.setOnClickListener() {
            findNavController().navigate(R.id.action_login_fragment_to_sign_up_options_fragment2)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}
