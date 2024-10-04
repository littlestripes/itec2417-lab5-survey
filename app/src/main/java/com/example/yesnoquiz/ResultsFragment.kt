package com.example.yesnoquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultsFragment : Fragment() {

    private lateinit var yesVotesText: TextView
    private lateinit var noVotesText: TextView
    private lateinit var resetButton: Button

    private lateinit var voteTallyViewModel: VoteTallyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_results, container, false)

        voteTallyViewModel = ViewModelProvider(requireActivity()).get(VoteTallyViewModel::class.java)

        yesVotesText = view.findViewById(R.id.yes_votes_text)
        noVotesText = view.findViewById(R.id.no_votes_text)
        resetButton = view.findViewById(R.id.reset_button)

        resetButton.setOnClickListener {
            // reset vote counts in the ViewModel first
            voteTallyViewModel.resetVotes()
            // then reflect the changes visually
            updateTallies()
            // requireContext grabs the current context the Fragment is associated with
            Toast.makeText(requireContext(), "Vote tallies were reset", Toast.LENGTH_SHORT).show()
        }

        updateTallies()

        return view
    }

    private fun updateTallies() {
        // update the displayed vote tallies to reflect the ViewModel's data.
        // uses string resources with placeholders to display current vote tallies
        yesVotesText.text = getString(R.string.yes_vote_tally, voteTallyViewModel.getYesVotes().toString())
        noVotesText.text = getString(R.string.no_vote_tally, voteTallyViewModel.getNoVotes().toString())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ResultsFragment.
         */
        @JvmStatic
        fun newInstance() = ResultsFragment()
    }
}