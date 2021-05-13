package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                        }
                        else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }

        }

        val login_btn = findViewById<Button>(R.id.btn_login)
        login_btn.setOnClickListener {
            loginUser()
        }

    }
}