package com.example.fragmenthomework1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Fragment1 : Fragment() {

    private lateinit var onColourChangeListener: OnColourChangeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onColourChangeListener = (context as? OnColourChangeListener) ?: throw AssertionError("Activity should implement interface")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        view.findViewById<Button>(R.id.buttonChangeColour).setOnClickListener {
            onColourChangeListener.onColourChangeClicked()
        }
        return view
    }

    interface OnColourChangeListener {
        fun onColourChangeClicked()
    }
}