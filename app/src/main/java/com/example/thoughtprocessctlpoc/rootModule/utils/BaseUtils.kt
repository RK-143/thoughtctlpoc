package com.example.thoughtprocessctlpoc.rootModule.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat

object BaseUtils {
    fun getDrawableByName(activity: Activity, drawableName: String): Drawable? {
        return ContextCompat.getDrawable(
            activity,
            getDrawableResource(
                activity,
                drawableName
            )
        )
    }
    fun getDrawableResource(context: Context, name: String): Int {
        var resourceID = 0
        val resources = context.resources
        val packageName = context.packageName
        try {

            resourceID = resources.getIdentifier(
                name, "drawable",
                packageName
            )

        } catch (e: Exception) {
            Log.e("Ex", e.toString())
        }
        return resourceID
    }
}