package com.example.boggle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentA = LetterChooseFragment()
        val fragmentB = ScoreFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerLetterChoose, fragmentA)
            .replace(R.id.fragmentContainerScore, fragmentB)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }
}