package com.example.fragmenthomework1

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout


private const val ARG_COLOUR = "colour"


class Fragment2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //val colour = it.getInt(ARG_COLOUR)
            //changeColourOfBackground(colour)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    fun changeColourOfBackground(colour: Int) {
        requireView().findViewById<FrameLayout>(R.id.fragment2).setBackgroundColor(colour)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            Fragment2().apply {
                arguments = Bundle().apply {
                    //putInt(ARG_COLOUR, colour)
                }
            }
    }


}