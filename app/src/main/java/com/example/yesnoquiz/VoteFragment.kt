package com.example.yesnoquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [VoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VoteFragment : Fragment() {

    private lateinit var yesButton: Button
    private lateinit var noButton: Button

    private lateinit var voteTallyViewModel: VoteTallyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vote, container, false)

        voteTallyViewModel = ViewModelProvider(requireActivity()).get(VoteTallyViewModel::class.java)

        yesButton = view.findViewById(R.id.yes_button)
        noButton = view.findViewById(R.id.no_button)

        yesButton.setOnClickListener {
            voteTallyViewModel.incrementYesVotes()
        }

        noButton.setOnClickListener {
            voteTallyViewModel.incrementNoVotes()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment VoteFragment.
         */
        @JvmStatic
        fun newInstance() = VoteFragment()
    }
}