package com.go0ose.agedeterminant.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.go0ose.agedeterminant.AppComponent
import com.go0ose.agedeterminant.R
import com.go0ose.agedeterminant.presentation.mainScreen.MainActivity

fun FragmentActivity.openFragment(container: Int, tag: String, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainActivity -> appComponent
        else -> this.applicationContext.appComponent
    }

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Fragment.showDialog(
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit
) {
    val builder = AlertDialog.Builder(this.requireContext())
    val dialogLayout = layoutInflater.inflate(R.layout.dialog_alert, null)
    val negativeButton = dialogLayout.findViewById<Button>(R.id.buttonNegativeAnswer)
    val positiveButton = dialogLayout.findViewById<Button>(R.id.buttonPositiveAnswer)
    builder.setCancelable(false)
    builder.setView(dialogLayout)

    val alertDialog = builder.create()
    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    positiveButton.setOnClickListener {
        onPositiveButtonClick()
        alertDialog.cancel()
    }
    negativeButton.setOnClickListener {
        onNegativeButtonClick()
        alertDialog.cancel()
    }
    alertDialog.show()
}