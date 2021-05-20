package com.example.linkapp.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.linkapp.OnboardingActivity
import com.example.linkapp.R

class OnboardingThreeFragment : Fragment(){
    override  fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {
        val view: View = inflater.inflate(R.layout.fragment_onboarding_three, container, false)

        val next_three = view.findViewById<Button>(R.id.btn_get_started)
        next_three.setOnClickListener {
            (activity as OnboardingActivity?)!!.nextThree()
        }

        return view
    }
}