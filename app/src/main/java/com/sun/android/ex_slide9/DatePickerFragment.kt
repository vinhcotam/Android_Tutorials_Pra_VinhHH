package com.sun.android.ex_slide9

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]
        val dialog = DatePickerDialog(requireActivity(), this, year, month, day)
        return dialog
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        (activity as? AlertDateTime)?.processDatePickerResult(year, month, day)
    }
}
