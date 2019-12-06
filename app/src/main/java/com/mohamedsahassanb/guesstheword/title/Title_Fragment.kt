package com.mohamedsahassanb.guesstheword.title


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.mohamedsahassanb.guesstheword.R
import kotlinx.android.synthetic.main.fragment_title_.view.*

/**
 * A simple [Fragment] subclass.
 */
class Title_Fragment : Fragment() {

    lateinit var tool: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tool = inflater.inflate(R.layout.fragment_title_, container, false)

       /* tool.btn_play.setOnClickListener(
            Navigation.createNavigateOnClickListener(Title_FragmentDirections.actionTitleFragmentToGameFragment())
        )

        */

        tool.btn_play.setOnClickListener {
            findNavController().navigate(Title_FragmentDirections.actionTitleFragmentToGameFragment())
        }

        return tool
    }


}
