package com.example.yesnoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class SurveyResultActivity : AppCompatActivity() {

    lateinit private var yesVotesText: TextView
    lateinit private var noVotesText: TextView
    lateinit private var continueButton: Button
    lateinit private var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        yesVotesText = findViewById(R.id.yes_votes_text)
        noVotesText = findViewById(R.id.no_votes_text)
        continueButton = findViewById(R.id.continue_button)
        resetButton = findViewById(R.id.reset_button)

        yesVotesText.text = "Yes: ${intent.getIntExtra(EXTRA_YES_RESULT, 0)}"
        noVotesText.text = "No: ${intent.getIntExtra(EXTRA_NO_RESULT, 0)}"

        continueButton.setOnClickListener {
            Intent().apply {
                // pass the mustReset flag back to MainActivity (indicates if the vote tallies need
                // to be reset
                putExtra(EXTRA_MUST_RESET, false)
                setResult(RESULT_OK, this)
                finish()
            }
        }

        resetButton.setOnClickListener {
            // explicitly let the user know what happened
            Toast.makeText(this, "Vote tallies reset", Toast.LENGTH_SHORT).show()
            Intent().apply {
                putExtra(EXTRA_MUST_RESET, true)
                setResult(RESULT_OK, this)
                finish()
            }
        }
    }
}