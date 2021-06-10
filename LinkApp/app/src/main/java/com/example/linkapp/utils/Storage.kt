package com.example.linkapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.NullPointerException

object Storage {
    private val storageIntance: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    private val currentUserRef: StorageReference
        get() = storageIntance.reference
            .child(FirebaseAuth.getInstance().currentUser?.uid ?: throw NullPointerException("UID is null"))

    fun pathToReference(path:String) = storageIntance.getReference(path)
}