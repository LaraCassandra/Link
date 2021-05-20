package com.example.linkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.linkapp.onboarding.OnboardingOneFragment
import com.example.linkapp.onboarding.OnboardingThreeFragment
import com.example.linkapp.onboarding.OnboardingTwoFragment

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        //REFERENCE TO FRAGMENTS
        val onboardingOneFragment = OnboardingOneFragment()

        //SET DEFAULT FRAGMENT
        //APPLY FRAGMENT TO FRAME
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_onboarding_fragment, onboardingOneFragment)
            commit()
        }
    }

    val onboardingTwoFragment = OnboardingTwoFragment()

    fun nextOne() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_onboarding_fragment, onboardingTwoFragment)
            addToBackStack(null)
            commit()
        }
    }

    val onboardingThreeFragment = OnboardingThreeFragment()

    fun nextTwo() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_onboarding_fragment, onboardingThreeFragment)
            addToBackStack(null)
            commit()
        }
    }

    fun nextThree() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun skip() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
