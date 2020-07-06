package com.lidor.lec4_tomer_color_picker.model

import android.text.Editable
import android.text.TextWatcher

interface PickerTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?)
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}