package com.example.weatherapp

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object DialogManager {
    fun locationSettingsDialog(context: Context,listener: Listener) {
        val builder = AlertDialog.Builder(context).create().apply {
            setTitle("Enable location?")
            setMessage("Do you want to enable location?")
            setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
                listener.onClick(null)
                dismiss()
            }
            setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
                dismiss()
            }
            show()
        }
    }
    fun searchBynameDialog(context: Context,listener: Listener) {
        val edName= EditText(context)
        val builder = AlertDialog.Builder(context).create().apply {
            setTitle("City name")
            setMessage("")
            setView(edName)
            setButton(AlertDialog.BUTTON_POSITIVE, "OK") { _, _ ->
                listener.onClick(edName.text.toString())
                dismiss()
            }
            setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
                dismiss()
            }
            show()
        }
    }
    interface Listener{
        fun onClick(name:String?)
    }
}