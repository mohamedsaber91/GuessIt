package com.mohamedsahassanb.guesstheword.game


import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.mohamedsahassanb.guesstheword.R
import kotlinx.android.synthetic.main.fragment_game_.view.*
import kotlinx.android.synthetic.main.fragment_score_.view.*

/**
 * A simple [Fragment] subclass.
 */
class Game_Fragment : Fragment() {


    lateinit var viewModel: GameViewModel
    lateinit var tool :View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tool = inflater.inflate(R.layout.fragment_game_, container, false)

        Log.e("GameFragment", "called ViewModelProviders.of")
        viewModel= ViewModelProviders.of(this).get(GameViewModel::class.java)



        tool.btn_next.setOnClickListener {
            viewModel.onCorrect()
        }

        tool.btn_skip.setOnClickListener {
            viewModel.onSkip()
        }


        viewModel.score.observe(this, Observer { newScore->
            tool.view_score.text=newScore.toString()
        })

        viewModel.word.observe(this, Observer { newWord ->
            tool.view_word.text=newWord
        })
        viewModel.eventGameFinished.observe(this, Observer { hasFinished ->
            if (hasFinished){
                gameFinished()
                viewModel.onGameFinished()
            }
        })

        viewModel.currentTime.observe(this, Observer { newTime->
            tool.view_time.text= DateUtils.formatElapsedTime(newTime)
        })
        return tool
    }





     fun gameFinished(){

        findNavController().navigate(Game_FragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value ?: 0))

    }






}
