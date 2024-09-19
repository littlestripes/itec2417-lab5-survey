package com.example.yesnoquiz

import androidx.lifecycle.ViewModel

class VoteTallyViewModel : ViewModel(){

    private var yesVotes = 0
    private var noVotes = 0

    fun getYesVotes(): Int {
        return yesVotes
    }

    fun getNoVotes(): Int {
        return noVotes
    }

    fun incrementYesVotes() {
        yesVotes++
    }

    fun incrementNoVotes() {
        noVotes++
    }

    fun resetVotes() {
        yesVotes = 0
        noVotes = 0
    }
}