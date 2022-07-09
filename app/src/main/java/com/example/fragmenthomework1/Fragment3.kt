package com.example.fragmenthomework1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

private const val ARG_COLOUR = "colour"

class Fragment3 : Fragment() {
    var colour: Int = -1
    private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            colour = it.getInt(ARG_COLOUR)
        }
        if (arguments == null) colour = getColourInt(R.color.yellow, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(colour: Int) =
            Fragment3().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLOUR, colour)
                }
            }
    }

    fun changeColourOfBackground(colour: Int) {
        requireView().findViewById<FrameLayout>(R.id.fragment3).setBackgroundColor(colour)
    }
}