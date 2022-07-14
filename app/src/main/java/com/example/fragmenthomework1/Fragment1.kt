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
    private lateinit var onReplaceListener: OnReplaceListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onColourChangeListener = (context as? OnColourChangeListener) ?: throw AssertionError("Activity should implement OnColourChangeListener")
        onReplaceListener = (context as? OnReplaceListener) ?: throw AssertionError("Activity should implement OnReplaceListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        view.findViewById<Button>(R.id.buttonChangeColour).setOnClickListener { onColourChangeListener.onColourChangeClicked() }
        view.findViewById<Button>(R.id.buttonReplaceFragment).setOnClickListener { onReplaceListener.onReplaceClicked() }
        return view
    }

    interface OnColourChangeListener {
        fun onColourChangeClicked()
    }

    interface OnReplaceListener {
        fun onReplaceClicked()
    }
}