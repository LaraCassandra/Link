package com.example.linkapp.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.linkapp.ChatsActivity
import com.example.linkapp.RegisterActivity
import com.example.linkapp.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.xwray.groupie.kotlinandroidextensions.Item
import java.lang.NullPointerException

object FirestoreUtil {
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val chatChannelCollectionRef = firestoreInstance.collection("chatChannels")

    private val currentUserDocRef: DocumentReference
    get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().currentUser?.uid
        ?: throw NullPointerException("UID is null")}")

    fun addUserListener(context: Context, onListen: (List<Item>) -> Unit): ListenerRegistration {
        return firestoreInstance.collection("users")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FIRESTORE", "User listner erorr", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                val items = mutableListOf<Item>()
                querySnapshot!!.documents.forEach {
                    if(it.id != FirebaseAuth.getInstance().currentUser?.uid)
                        items.add(ContactItem(it.toObject(User::class.java), it.id, context))
                }
                onListen(items)
            }
    }

    fun removeListener(registration: ListenerRegistration) = registration.remove()

    fun getOrCreateChatChannel(
        otherUserId: String,
        onComplete: (channelId: String) -> Unit){
        currentUserDocRef.collection("engagedChatChannels")
            .document(otherUserId).get().addOnSuccessListener {
                if(it.exists()){
                    onComplete(it["channelID"] as String)
                    return@addOnSuccessListener
                }

                val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
                val newChannel = chatChannelCollectionRef.document()
                newChannel.set(ChatChannel(mutableListOf(currentUserId, otherUserId)))

                currentUserDocRef
                    .collection("engagedChatChannels")
                    .document(otherUserId)
                    .set(mapOf("channelId" to newChannel.id))
                firestoreInstance.collection("users").document(otherUserId)
                    .collection("engagedChatChannels")
                    .document(currentUserId)
                    .set(mapOf("channelId" to newChannel.id))
                onComplete(newChannel.id)
            }
    }

    fun addChatMessagesListener(channelId: String,
                                context: Context,
                                onListen: (List<Item>) -> Unit): ListenerRegistration{
        return chatChannelCollectionRef.document(channelId).collection("messages")
            .orderBy("time")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null){
                    Log.e("FIRESTORE", "ChatMessagesListener error.", firebaseFirestoreException)
                    return@addSnapshotListener
                }
                val items = mutableListOf<Item>()
                querySnapshot!!.documents.forEach {
                    items.add(TextMessageItem(it.toObject(TextMessage::class.java), context))
                    onListen(items)
                }
            }
    }

}

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

//    fun updateCurrentUser(name: String = "", email: String = ""){
//        val userFieldMap = mutableMapOf<String, Any>()
//        if (name.isNotBlank()) userFieldMap["name"] = name
//        if (email.isNotBlank()) userFieldMap["email"] = email
//        currentUserDocReference.update(userFieldMap)
//    }


}