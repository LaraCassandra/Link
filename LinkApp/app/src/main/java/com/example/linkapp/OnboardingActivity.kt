package com.example.linkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.linkapp.onboarding.OnboardingOneFragment
import com.example.linkapp.onboarding.OnboardingThreeFragment
import com.example.linkapp.onboarding.OnboardingTwoFragment

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        //REFERENCE TO FRAGMENTS
        val onboardingOneFragment = OnboardingOneFragment()
        val onboardingTwoFragment = OnboardingTwoFragment()
        val onboardingThreeFragment = OnboardingThreeFragment()

        //SET DEFAULT FRAGMENT
        //APPLY FRAGMENT TO FRAME
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_fragment, onboardingOneFragment)
            commit()
        }

        //APPLY SECOND ONBOARDING SCREEN TO FRAME
        val firstNext = findViewById<Button>(R.id.btn_first_next)
        firstNext.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_fragment, onboardingTwoFragment)
                addToBackStack(null)
                commit()
            }
        }
//
//        //APPLY THIRD ONBOARDING SCREEN TO FRAME
//        val secondNext = findViewById<Button>(R.id.btn_second_next)
//        secondNext.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fl_fragment, onboardingThreeFragment)
//                addToBackStack(null)
//                commit()
//            }
//        }
    }
}