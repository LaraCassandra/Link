package com.example.linkapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.linkapp.R
import com.example.linkapp.utils.Firestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.btn_edit
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        view.apply {
            btn_edit.setOnClickListener{
                Firestore.updateCurrentUser(et_name.text.toString(), et_email.text.toString())
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Firestore.getCurrentUser { user ->
        if(this@ProfileFragment.isVisible){
            et_name.setText(user.displayName)
            et_email.setText(user.email)
        }

        }
    }
}

