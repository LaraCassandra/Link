package com.example.linkapp.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.linkapp.R
import com.example.linkapp.utils.Firestore
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        view.apply {

//            btn_edit.setOnClickListener{
//            updateProfile()
//            //(activity as Firestore?)!!.updateCurrentUser(et_name.text.toString(), et_email.text.toString())
//            }

        }

        return view
    }

    private fun updateProfile(){
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = et_name.text.toString()
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Log.d(TAG, "User profile updated")
                }
            }
    }

//    override fun onStart() {
//        super.onStart()
//        val user = Firebase.auth.currentUser
//        user?.let{
//            for (profile in it.providerData){
//                val userName = profile.displayName
//                val email = profile.email
//
//                et_name.setText(userName)
//                et_email.setText(email)
//
//            }
//        }
//    }
}

