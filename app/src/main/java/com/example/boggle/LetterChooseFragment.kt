package com.example.boggle

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.boggle.databinding.FragmentLetterChooseBinding
import com.google.android.material.button.MaterialButton
import kotlin.math.abs

class LetterChooseFragment: Fragment() {
    private var _binding: FragmentLetterChooseBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private var clicked = intArrayOf()
    private lateinit var word: String
    private var buttonIds = arrayOf<Button>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterChooseBinding.inflate(inflater, container, false)
        word = ""
        buttonIds = arrayOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9, binding.button10, binding.button11,
            binding.button12, binding.button13, binding.button14, binding.button15,
        )
        newBoard()
        for (button in buttonIds) {
            button.setOnClickListener {
                onLetterClick(button)
            }
        }
        binding.clearButton.setOnClickListener{
            resetBoard()
        }
        return binding.root
    }

    private fun onLetterClick(button: Button) {
        val buttonName = resources.getResourceEntryName(button.id)!!
        val number = buttonName.substring(6).toInt()
        if (clicked.isEmpty()) {
            clicked += number
            word += button.text
            button.setBackgroundColor(Color.parseColor("#c6cfc8"))
            button.isEnabled = false
        } else {
            val lastClicked = clicked.last()
            val lastRow = lastClicked.div(4)
            val lastCol = lastClicked % 4
            val newRow = number.div(4)
            val newCol = number % 4
            val rowDistance = newRow - lastRow
            val colDistance = newCol - lastCol
            if (number == lastClicked || clicked.contains(number)) {
                Toast.makeText(context, "Cannot reuse buttons", Toast.LENGTH_SHORT).show()            }
            if ((lastRow == newRow && abs(colDistance) == 1) || (lastCol == newCol && abs(rowDistance) == 1)) {
                clicked += number
                word += button.text
                button.setBackgroundColor(Color.parseColor("#c6cfc8"))
                button.isEnabled = false
            } else if (abs(colDistance) == 1 && abs(rowDistance) == 1) {
                clicked += number
                word += button.text
                button.setBackgroundColor(Color.parseColor("#c6cfc8"))
                button.isEnabled = false
            } else {
                Toast.makeText(context, "Cannot use non-adjacent letter", Toast.LENGTH_SHORT).show()
            }
        }
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
        resetBoard()
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as? Button
            button?.text = letters[i].toString()
        }
    }

    private fun canClick() {
        //check for adjacency and previously clicked letters?
    }
    private fun resetBoard() {
        buttonIds.forEach { button ->
            button.isEnabled = true
            button.setBackgroundColor(Color.parseColor("#42474f"))
        }
        clicked = intArrayOf()
        word = ""
    }

    private fun submit(word: String) {
        //if it is a word, get the score
        val score = letterScore(word)
        //else

    }
    private fun letterScore(word: String) {

    }


}