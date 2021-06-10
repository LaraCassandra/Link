package com.example.linkapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.linkapp.model.User
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.Firestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // LOGIN BUTTON
        val tv_login = findViewById<TextView>(R.id.tv_login)
        tv_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        // REGISTER USER FUNCTION
        fun registerUser(){

            // VARIABLES
            val email: String = findViewById<EditText>(R.id.et_email).text.toString().trim{ it <= ' '}
            val displayName: String = findViewById<EditText>(R.id.et_name).text.toString().trim{ it <= ' '}
            val password: String = findViewById<EditText>(R.id.et_password).text.toString().trim{ it <= ' '}

            // VALIDATION
            if(email == "" || password == ""){
                showErrorSnackBar("Please enter your email and password", true)
            }
            else {
                // REGISTER WITH FIREBASE
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                                if (task.isSuccessful){
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    //showErrorSnackBar("Successfully register user", false)

                                    // CREATE USER DATA
                                    val user = User(
                                        firebaseUser.uid,
                                        displayName,
                                        email
                                    )

                                    // ADD TO FIRESTORE
                                    Firestore.registerUser(this, user)
                                }
                                else {
                                    showErrorSnackBar(task.exception!!.message.toString(), true)
                                }
                            }

            }
        }

        // ON REGISTER BUTTON CLICK
        val register_btn = findViewById<Button>(R.id.btn_register)
        register_btn.setOnClickListener {
            registerUser()
        }
    }

    fun userRegisteredSuccess(uid: String) {
        showErrorSnackBar("Success on register", false)

        // NAVIGATION
        val intent = Intent(this, OnboardingActivity::class.java)
        intent.putExtra(Constants.LOGGED_IN_ID, uid)
        startActivity(intent)
        finish()

    }
}