package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
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
            val name: String = findViewById<EditText>(R.id.et_name).text.toString().trim{ it <= ' '}
            val password: String = findViewById<EditText>(R.id.et_password).text.toString().trim{ it <= ' '}

            // VALIDATION
            if(email == "" || password == ""){
                showErrorSnackBar("Please enter your email and password", true);
            }
            else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                            OnCompleteListener<AuthResult>{ task ->
                                if (task.isSuccessful){
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    showErrorSnackBar("Successfully register user", false)
                                }
                                else {
                                    showErrorSnackBar(task.exception!!.message.toString(), true)
                                }
                            }
                    )
            }
        }

        // ON REGISTER BUTTON CLICK
        val register_btn = findViewById<Button>(R.id.btn_register)
        register_btn.setOnClickListener {
            registerUser()
        }

    }
}