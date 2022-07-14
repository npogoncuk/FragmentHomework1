package com.example.fragmenthomework1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

private const val ARG_COLOUR = "colour"

class Fragment3 : Fragment(), BackgroundChangeable {
    private var colour: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            colour = it.getInt(ARG_COLOUR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) colour = getColourInt(R.color.yellow, requireContext())
    }

    override fun onStart() {
        super.onStart()
        setColourOfBackground(colour)
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

    private fun setColourOfBackground(colour: Int) {
        requireView().findViewById<FrameLayout>(R.id.fragment3).setBackgroundColor(colour)
    }

    override fun changeColourOfBackground() {
        colour = if (colour == getColourInt(R.color.yellow, requireContext())) getColourInt(R.color.black, requireContext())
        else getColourInt(R.color.yellow, requireContext())
        setColourOfBackground(colour)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_COLOUR, colour)
    }
}