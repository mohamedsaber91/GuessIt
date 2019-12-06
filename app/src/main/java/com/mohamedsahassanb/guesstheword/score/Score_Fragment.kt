package com.mohamedsahassanb.guesstheword.score


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.mohamedsahassanb.guesstheword.R
import kotlinx.android.synthetic.main.fragment_score_.view.*

/**
 * A simple [Fragment] subclass.
 */
class Score_Fragment : Fragment() {

    lateinit var tool:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tool = inflater.inflate(R.layout.fragment_score_, container, false)

        tool.score_text.text=Score_FragmentArgs.fromBundle(arguments!!).finalScore.toString()
        tool.btn_play_again.setOnClickListener {
              onPlayAgain()
        }
        return tool
    }

    private fun onPlayAgain(){
          findNavController().navigate(Score_FragmentDirections.actionScoreFragmentToGameFragment2())
    }
}
