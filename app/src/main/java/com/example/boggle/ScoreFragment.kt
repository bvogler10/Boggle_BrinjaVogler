package com.example.boggle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.boggle.databinding.FragmentScoreBinding


class ScoreFragment: Fragment() {
    interface ScoreFragmentListener{
        fun newGame()
    }
    private var listener: ScoreFragment.ScoreFragmentListener? = null

    private var _binding: FragmentScoreBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        binding.newGame.setOnClickListener {

        }

        return binding.root
    }

    private fun letterScore(word: String) {

    }
    private fun newGame() {
        listener?.newGame()
    }
}