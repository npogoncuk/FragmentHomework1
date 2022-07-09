package com.example.fragmenthomework1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat

private const val KEY_IS_COLOUR_CHANGED = "isColourChanged"
private const val KEY_IS_FRAGMENTS_REPLACED = "isFragmentsReplaced"

class MainActivity : AppCompatActivity(), Fragment1.OnColourChangeListener, Fragment1.OnReplaceListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment1_container, Fragment1())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment2_container, Fragment2.newInstance(ContextCompat.getColor(this, R.color.blue)))
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment3_container, Fragment3.newInstance(ContextCompat.getColor(this, R.color.yellow)))
            .commit()

    }

    override fun onResume() {
        super.onResume()
        if (isColourChanged) {
            isColourChanged = !isColourChanged
            onColourChangeClicked()
        }
        if (isFragmentsReplaced) {
            onReplaceClicked()
        }
    }

    private var isColourChanged = false
    override fun onColourChangeClicked() {
        if (!isFragmentsReplaced) {
            (supportFragmentManager.findFragmentById(R.id.fragment2_container) as Fragment2)
                .changeColourOfBackground(getColourInt( if (!isColourChanged) R.color.red else R.color.blue, this))
            (supportFragmentManager.findFragmentById(R.id.fragment3_container) as Fragment3)
                .changeColourOfBackground(getColourInt(if (!isColourChanged) R.color.black else R.color.yellow, this))
        } else {
            (supportFragmentManager.findFragmentById(R.id.fragment2_container) as Fragment3)
                .changeColourOfBackground(getColourInt( if (!isColourChanged) R.color.red else R.color.blue, this))
            (supportFragmentManager.findFragmentById(R.id.fragment3_container) as Fragment2)
                .changeColourOfBackground(getColourInt(if (!isColourChanged) R.color.black else R.color.yellow, this))
        }
        isColourChanged = !isColourChanged
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_COLOUR_CHANGED, isColourChanged)
        outState.putBoolean(KEY_IS_FRAGMENTS_REPLACED, isFragmentsReplaced)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isColourChanged = savedInstanceState.getBoolean(KEY_IS_COLOUR_CHANGED)
        isFragmentsReplaced = savedInstanceState.getBoolean(KEY_IS_FRAGMENTS_REPLACED)
    }

    private var isFragmentsReplaced = false
    override fun onReplaceClicked() {
        val fragmentCenter =
            if (!isFragmentsReplaced)
                Fragment3.newInstance(if (isColourChanged) getColourInt(R.color.black, this) else getColourInt(R.color.yellow, this))
            else Fragment2.newInstance(if (isColourChanged) getColourInt(R.color.red, this) else getColourInt(R.color.blue, this))
        val fragmentBottom =
            if (isFragmentsReplaced)
                Fragment3.newInstance(if (isColourChanged) getColourInt(R.color.black, this) else getColourInt(R.color.yellow, this))
            else Fragment2.newInstance(if (isColourChanged) getColourInt(R.color.red, this) else getColourInt(R.color.blue, this))

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment2_container, fragmentBottom)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment3_container, fragmentCenter)
            .commit()

        isFragmentsReplaced = !isFragmentsReplaced
    }
}