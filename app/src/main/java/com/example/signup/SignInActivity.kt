package com.example.signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        textViewSignUp.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (editTextEmail.text.toString().isNullOrEmpty() || editTextPassword.text.toString()
                    .isNullOrEmpty())
                textViewResponse.text = "Email Address or Password is not provided"
            else {
                auth.signInWithEmailAndPassword(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            textViewResponse.text =
                                "Sign In Successful."
                            val user = auth.currentUser
                            updateUI(user,editTextEmail.text.toString())
                        } else {
                            textViewResponse.text = "Invalid Email or Password"
                        }
                    }
            }
        }

    }

    private fun updateUI(currentUser: FirebaseUser?,emailAdd:String) {

    }

}