package com.example.linkapp.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.linkapp.OnboardingActivity
import com.example.linkapp.R

class OnboardingOneFragment : Fragment(){
    override  fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {
        val view: View = inflater.inflate(R.layout.fragment_onboarding_one, container, false)

        val next_one = view.findViewById<Button>(R.id.btn_first_next)
        next_one.setOnClickListener {
            (activity as OnboardingActivity?)!!.nextOne()
        }

        val skip = view.findViewById<TextView>(R.id.tv_skip)
        skip.setOnClickListener {
            (activity as OnboardingActivity?)!!.skip()
        }

        return view
    }
}