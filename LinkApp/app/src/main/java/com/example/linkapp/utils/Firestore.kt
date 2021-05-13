package com.example.linkapp.utils

import com.example.linkapp.RegisterActivity
import com.example.linkapp.model.User
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    // INITIATE FIRESTORE
    private val db = FirebaseFirestore.getInstance()

    // ADD USER TO FIREBASE WHEN THEY REGISTER
    fun registerUser(activity: RegisterActivity, userInfo: User){

        
    }
}