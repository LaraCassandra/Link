package com.example.linkapp.utils

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.linkapp.ChatsActivity
import com.example.linkapp.RegisterActivity
import com.example.linkapp.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Firestore {

    // INITIATE FIRESTORE
    private val db = FirebaseFirestore.getInstance()

    // ADD USER TO FIREBASE WHEN THEY REGISTER
    fun registerUser(activity: RegisterActivity, userInfo: User){

        db.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess(userInfo.id)
            }
            .addOnFailureListener {
                activity.showErrorSnackBar("Error while registering the user", true)
            }
    }

    fun getUserInfoById(activity: ChatsActivity, uid: String){
        db.collection(Constants.USERS)
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if(document != null){
                    val user = document.toObject(User::class.java)!!
                    activity.setUserInfo(user)
                }
                else {
                    Toast.makeText(activity, "The user info is empty", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(activity, exception.message, Toast.LENGTH_LONG).show()
                Log.d(TAG, "get dailed with ", exception)
            }
    }
}