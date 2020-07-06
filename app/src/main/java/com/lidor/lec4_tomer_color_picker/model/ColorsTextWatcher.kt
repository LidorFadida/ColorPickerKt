package com.lidor.lec4_tomer_color_picker.model

import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import com.lidor.lec4_tomer_color_picker.MAX_RGB_REPRESENTATION


class ColorsTextWatcher(private val etTarget: EditText, private val sbTarget: SeekBar) :
     View.OnFocusChangeListener , PickerTextWatcher{
    init {
        this.etTarget.onFocusChangeListener = this
    }
    override fun afterTextChanged(s: Editable?) {
        this.sbTarget.progress = validateValue(s)
        val value = this.etTarget.text.toString()
        if (value.isEmpty())
            return
        if (value.toInt() > MAX_RGB_REPRESENTATION)
            this.sbTarget.progress = MAX_RGB_REPRESENTATION
        etTarget.setSelection(etTarget.length())
    }

    private fun validateValue(editable: Editable?): Int {
        if (editable != null) {
            val value = "$editable"
            if (value.isNotEmpty())
                return "$editable".toInt()
        }
        return 0
    }
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus)
            if ((v as EditText).text.isEmpty())
                v.setText("0")
    }
}