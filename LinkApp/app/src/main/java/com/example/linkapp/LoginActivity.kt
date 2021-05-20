package com.example.linkapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.linkapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // REGISTER BUTTON
        val tv_register = findViewById<TextView>(R.id.tv_register)
        tv_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // LOGIN USER FUNCTION
        fun loginUser(){

            // VARIABLES
            val email: String = findViewById<EditText>(R.id.et_email).text.toString().trim{ it <= ' '}
            val password: String = findViewById<EditText>(R.id.et_password).text.toString().trim{ it <= ' '}

            // VALIDATION
            if (email == "" || password == ""){
                showErrorSnackBar("Please enter your details", true)
            }
            else {
                // LOGIN USING FIREBASE
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            showErrorSnackBar("Successful", false)
                            loginUserSuccess(firebaseUser.uid)
                        }
                        else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }

        }

        // LOGIN BUTTON ON CLICK
        val btn_login = findViewById<Button>(R.id.btn_login1)
        btn_login.setOnClickListener {
            loginUser()
        }

    }

    private fun loginUserSuccess(uid: String){

        // NAVIGATION
            val intent = Intent(this, ChatsActivity::class.java)
            intent.putExtra(Constants.LOGGED_IN_ID, uid)
            startActivity(intent)
            finish()

    }
}