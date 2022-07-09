package com.example.fragmenthomework1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), Fragment1.OnColourChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment1_container, Fragment1())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment2_container, Fragment2())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment3_container, Fragment3())
            .commit()
    }

    private var isColourChanged = false
    override fun onColourChangeClicked() {
        (supportFragmentManager.findFragmentById(R.id.fragment2_container) as Fragment2)
            .changeColourOfBackground(ContextCompat.getColor(this, if (!isColourChanged) R.color.red else R.color.blue))
        (supportFragmentManager.findFragmentById(R.id.fragment3_container) as Fragment3)
            .changeColourOfBackground(ContextCompat.getColor(this, if (!isColourChanged) R.color.black else R.color.yellow))
        isColourChanged = !isColourChanged
    }
}