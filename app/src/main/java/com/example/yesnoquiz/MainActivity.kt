package com.example.yesnoquiz

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var containerView: View
    private lateinit var bottomNavigationView: BottomNavigationView

    val voteTallyViewModel: VoteTallyViewModel by lazy {
        ViewModelProvider(this).get(VoteTallyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        containerView = findViewById(R.id.fragment_container)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.show_vote -> {
                    showFragment("VOTE")
                    true
                }
                R.id.show_results -> {
                    showFragment("RESULTS")
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun showFragment(tag: String) {
        // is the fragment with given tag NOT on screen?
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            when (tag) {
                "VOTE" -> {
                    // display the voting interface
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, VoteFragment.newInstance(), "VOTE")
                        .commit()
                }
                "RESULTS" -> {
                    // display the results
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ResultsFragment.newInstance(), "RESULTS")
                        .commit()
                }
            }
        }
    }
}