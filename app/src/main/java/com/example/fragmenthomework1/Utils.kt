package com.example.fragmenthomework1

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun getColourInt(@ColorRes id: Int, context: Context) = ContextCompat.getColor(context, id)