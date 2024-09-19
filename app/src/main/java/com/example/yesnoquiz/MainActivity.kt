package com.example.yesnoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val voteTallyViewModel: VoteTallyViewModel by lazy {
        ViewModelProvider(this).get(VoteTallyViewModel::class.java)
    }

    lateinit private var yesButton: Button
    lateinit private var noButton: Button
    lateinit private var yesVotesText: TextView
    lateinit private var noVotesText: TextView
    lateinit private var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        yesButton = findViewById(R.id.yes_button)
        noButton = findViewById(R.id.no_button)
        yesVotesText = findViewById(R.id.yes_votes_text)
        noVotesText = findViewById(R.id.no_votes_text)
        resetButton = findViewById(R.id.reset_button)

        fun updateVoteTotals() {
            yesVotesText.text = voteTallyViewModel.getYesVotes().toString()
            noVotesText.text = voteTallyViewModel.getNoVotes().toString()
        }

        yesButton.setOnClickListener {
            voteTallyViewModel.incrementYesVotes()
            updateVoteTotals()
        }

        noButton.setOnClickListener {
            voteTallyViewModel.incrementNoVotes()
            updateVoteTotals()
        }

        resetButton.setOnClickListener {
            voteTallyViewModel.resetVotes()
            updateVoteTotals()
        }

        updateVoteTotals()
    }
}