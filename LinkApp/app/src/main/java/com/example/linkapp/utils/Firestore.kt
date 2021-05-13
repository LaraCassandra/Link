package com.example.linkapp.utils

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
}