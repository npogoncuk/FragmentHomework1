package com.example.fragmenthomework1

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), Fragment1.OnColourChangeListener,
    Fragment1.OnReplaceListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragmentTo(Fragment1(), R.id.fragment1_container)
            addFragmentTo(
                Fragment2.newInstance(getColourInt(R.color.blue, this)),
                R.id.fragment2_container
            )
            addFragmentTo(
                Fragment3.newInstance(getColourInt(R.color.yellow, this)),
                R.id.fragment3_container
            )
        }

    }

    private fun addFragmentTo(fragment: Fragment, @IdRes container: Int) {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .add(container, fragment)
            .commit()
    }


    override fun onColourChangeClicked() {
        (supportFragmentManager.findFragmentById(R.id.fragment2_container) as BackgroundChangeable).changeColourOfBackground()
        (supportFragmentManager.findFragmentById(R.id.fragment3_container) as BackgroundChangeable).changeColourOfBackground()
    }

    override fun onReplaceClicked() {
        val oldCenterFragment = supportFragmentManager.findFragmentById(R.id.fragment2_container)!!
        val oldBottomFragment = supportFragmentManager.findFragmentById(R.id.fragment3_container)!!
        supportFragmentManager
            .beginTransaction()
            .remove(oldCenterFragment)
            .add(R.id.fragment2_container, recreateFragment(oldBottomFragment))
            .commit()

        supportFragmentManager
            .beginTransaction()
            .remove(oldBottomFragment)
            .add(R.id.fragment3_container, recreateFragment(oldCenterFragment))
            .commit()
    }

    private fun recreateFragment(f: Fragment): Fragment {
        val savedState: Fragment.SavedState = supportFragmentManager.saveFragmentInstanceState(f)!!
        val newInstance = f.javaClass.newInstance()
        newInstance.setInitialSavedState(savedState)
        return newInstance
    }
}