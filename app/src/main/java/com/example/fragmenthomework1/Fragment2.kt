package com.example.fragmenthomework1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


private const val ARG_COLOUR = "colour"


class Fragment2 : Fragment(), BackgroundChangeable {
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
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) colour = getColourInt(R.color.blue, requireContext())
    }

    override fun onStart() {
        super.onStart()
        setColourOfBackground(colour)
    }

    private fun setColourOfBackground(colour: Int) {
        requireView().findViewById<FrameLayout>(R.id.fragment2).setBackgroundColor(colour)
    }

    companion object {
        @JvmStatic
        fun newInstance(colour: Int) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLOUR, colour)
                }
            }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_COLOUR, colour)
    }

    override fun changeColourOfBackground() {
        colour = if (colour == getColourInt(R.color.blue, requireContext())) getColourInt(R.color.red, requireContext())
        else getColourInt(R.color.blue, requireContext())
        setColourOfBackground(colour)
    }


}