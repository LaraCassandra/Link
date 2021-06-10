package com.example.linkapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.linkapp.model.User
import com.example.linkapp.utils.Constants
import com.example.linkapp.utils.Firestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {

    //NOTIFICATION CONSTANT VALUES
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"

    //CREATE NOTIFICATION ID
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // CALL FUNCTION TO ALLOW US TO CREATE NOTIFICATIONS
        createNotificaitonChannel()

        //CREATE INTENT THAT WILL BE PENDING
        val intent = Intent(this, OnboardingActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            // MAKES SURE THAT WHEN YOU OPEN THE APP IT DOESN'T OPEN THE ACTIVITY ON TOP OF THE OPEN ACTIVITY
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val registerNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Link App")
            .setContentText("A warm welcome from Link")
            .setSmallIcon(R.drawable.ic_baseline_tag_faces_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        // LOGIN BUTTON
        val tv_login = findViewById<TextView>(R.id.tv_login)
        tv_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // CREATE ANOTHER INSTANCE OF THE NOTIFICATION MANAGER
        val notificationManager = NotificationManagerCompat.from(this)

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
            notificationManager.notify(NOTIFICATION_ID, registerNotification)
        }
    }

    fun createNotificaitonChannel() {
        // CHECK SDK VERSION (GREATER THAN OREO VERSION)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            // CREATE NOTIFICATION CHANNEL AND SEND NAME, ID & IMPORTANCE
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                // SET THE NOTIFICATION LED COLOUR
                enableLights(true)
                lightColor = Color.YELLOW
            }
            // INSTRUCT SYSTEM TO POST NOTIFICATION AS A NOTIFICATION*
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // CALL MANAGER AND CREATE CHANNEL
            manager.createNotificationChannel(channel)
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