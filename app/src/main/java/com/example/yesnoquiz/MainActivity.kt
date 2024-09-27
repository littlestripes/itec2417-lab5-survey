package com.example.yesnoquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
const val EXTRA_YES_RESULT = "com.example.yesnoquiz.YES_RESULT"
const val EXTRA_NO_RESULT = "com.example.yesnoquiz.NO_RESULT"
const val EXTRA_MUST_RESET = "com.example.yesnoquiz.MUST_RESET"

class MainActivity : AppCompatActivity() {

    private val surveyResultResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> handleActivityResult(result)
    }

    private val voteTallyViewModel: VoteTallyViewModel by lazy {
        ViewModelProvider(this).get(VoteTallyViewModel::class.java)
    }

    lateinit private var yesButton: Button
    lateinit private var noButton: Button
    lateinit private var resultButton: Button

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
        resultButton = findViewById(R.id.result_button)

        yesButton.setOnClickListener {
            voteTallyViewModel.incrementYesVotes()
            Log.d(TAG, "New yes count: ${voteTallyViewModel.getYesVotes()}")
        }

        noButton.setOnClickListener {
            voteTallyViewModel.incrementNoVotes()
            Log.d(TAG, "New no count: ${voteTallyViewModel.getNoVotes()}")
        }

        resultButton.setOnClickListener {
            showResults()
        }
    }

    private fun showResults() {
        Intent(this, SurveyResultActivity::class.java).apply {
            // "sending" the yes and no vote tallies over to SurveyResultActivity
            putExtra(EXTRA_YES_RESULT, voteTallyViewModel.getYesVotes())
            putExtra(EXTRA_NO_RESULT, voteTallyViewModel.getNoVotes())
            surveyResultResultLauncher.launch(this)
        }
    }

    private fun handleActivityResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val mustReset = intent?.getBooleanExtra(EXTRA_MUST_RESET, false) ?: false
            if (mustReset) {
                // if the user pressed the Reset button in SurveyResultActivity, the vote tallies
                // will be reset here
                voteTallyViewModel.resetVotes()
            }
        }
    }
}