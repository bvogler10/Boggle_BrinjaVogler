package com.example.boggle

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), LetterChooseFragment.LetterChooseFragmentListener, ScoreFragment.ScoreFragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    override fun newGameClicked() {
        Log.d("INTERFACE", "NewGame in MainActivity called")
        val letterChooseFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerLetterChoose) as LetterChooseFragment?
        letterChooseFragment?.newGame()
    }
    override fun updateScore(score: Int) {
        Log.d("INTERFACE", "Submit in MainActivity called")
        val scoreFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerScore) as ScoreFragment?
        scoreFragment?.updateScore(score)
    }
}