package com.example.boggle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.boggle.databinding.FragmentLetterChooseBinding
import com.google.android.material.button.MaterialButton

class LetterChooseFragment: Fragment() {
    private var _binding: FragmentLetterChooseBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterChooseBinding.inflate(inflater, container, false)
        newBoard()
        val buttonIds = arrayOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9, binding.button10, binding.button11,
            binding.button12, binding.button13, binding.button14, binding.button15,
        )
        for (button in buttonIds) {
            button.setOnClickListener {
                onClick(button.id)
            }
        }
        return binding.root
    }

    private fun onClick(buttonId: Int) {

        Toast.makeText(this, "Button with ID $binding.buttonId.text clicked", Toast.LENGTH_SHORT).show()
    }

    private fun generateBoard(): List<Char> {
        val vowels = listOf('A', 'E', 'I', 'O', 'U')
        val consonants = ('A'..'Z').filter { it !in vowels }
        val random = java.util.Random()
        val vowelLetters = (2..5).map { vowels[random.nextInt(vowels.size)] }
        val consonantLetters = (1..14).map { consonants[random.nextInt(consonants.size)] }
        return (vowelLetters + consonantLetters).shuffled(random)
    }

    private fun newBoard() {
        val letters = generateBoard()
        val gridLayout = binding.gridLayout

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as? Button
            button?.text = letters[i].toString()
        }
    }

    private fun canClick() {
        //check for adjacency and previously clicked letters?
    }
    private fun resetBoard() {
        //unclick all letters
    }

    private fun submit(word: String) {
        //if it is a word, get the score
        val score = letterScore(word)
        //else

    }
    private fun letterScore(word: String) {

    }


}