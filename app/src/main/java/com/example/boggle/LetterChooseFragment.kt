package com.example.boggle

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.boggle.databinding.FragmentLetterChooseBinding
import kotlin.math.abs

class LetterChooseFragment: Fragment() {
    interface LetterChooseFragmentListener{
        fun submitWord(word: CharSequence)
    }
    var listener: LetterChooseFragmentListener? = null
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
        updateWord(word)
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
        binding.submitButton.setOnClickListener{
            listener?.submitWord(binding.wordDisplay.text)
            resetBoard()
        }
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FragmentLifecycle", "LetterChooseFragment attached")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("FragmentLifecycle", "LetterChooseFragment detached")
    }
    private fun onLetterClick(button: Button) {
        val buttonName = resources.getResourceEntryName(button.id)!!
        val number = buttonName.substring(6).toInt()
        if (clicked.isEmpty()) {
            clicked += number
            word += button.text
            button.setBackgroundColor(Color.parseColor("#c6cfc8"))
            button.isEnabled = false
            updateWord(word)
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
                updateWord(word)
            } else if (abs(colDistance) == 1 && abs(rowDistance) == 1) {
                clicked += number
                word += button.text
                button.setBackgroundColor(Color.parseColor("#c6cfc8"))
                button.isEnabled = false
                updateWord(word)
            } else {
                Toast.makeText(context, "Cannot use non-adjacent letter", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateWord(word: String) {
        binding.wordDisplay.text = word
    }

    private fun generateBoard(): List<Char> {
        val vowels = listOf('A', 'E', 'I', 'O', 'U')
        val consonants = ('A'..'Z').filter { it !in vowels }
        val random = java.util.Random()
        val vowelLetters = (2..8).map { vowels[random.nextInt(vowels.size)] }
        val consonantLetters = (1..(16 - vowelLetters.size)).map { consonants[random.nextInt(consonants.size)] }
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

    private fun resetBoard() {
        buttonIds.forEach { button ->
            button.isEnabled = true
            button.setBackgroundColor(Color.parseColor("#42474f"))
        }
        clicked = intArrayOf()
        word = ""
        updateWord(word)
    }

    fun newGame() {
        Log.d("FRAGMENT_COMM", "newGame clicked")
        newBoard()
        resetBoard()
    }

}