package com.example.brewbuddy.utils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.brewbuddy.R

object ToolbarManager {

    fun updateToolbarTitle(activity: AppCompatActivity, title: String) {
        // Find the toolbar in your activity
        val toolbar = activity.findViewById<ConstraintLayout>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.tvGreeting)
        titleTextView.text = title
    }

    fun setCustomGreeting(activity: AppCompatActivity, userName: String? = null) {
        val toolbar = activity.findViewById<ConstraintLayout>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.tvGreeting)

        val name = userName ?: "Guest"
        val greeting = "Good day"
        titleTextView.text = "$greeting, $name"
    }
}