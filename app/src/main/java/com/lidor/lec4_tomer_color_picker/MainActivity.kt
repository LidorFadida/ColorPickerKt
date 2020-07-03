package com.lidor.lec4_tomer_color_picker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lidor.lec4_tomer_color_picker.model.ColorsTextWatcher

private const val hexFormat: String = "#%02X%02X%02X"

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    companion object {
        const val MAX_RGB_REPRESENTATION: Int = 255
        const val DEFAULT_RAB_REPRESENTATION: Int = 128
        const val DEFAULT_ALPHA_REPRESENTATION: Float = 1.0F
    }

    private lateinit var tvAlpha: TextView
    private lateinit var tvColorHex: TextView
    private lateinit var etRed: EditText
    private lateinit var etGreen: EditText
    private lateinit var etBlue: EditText
    private lateinit var seekRed: SeekBar
    private lateinit var seekBlue: SeekBar
    private lateinit var seekGreen: SeekBar
    private lateinit var seekAlpha: SeekBar
    private lateinit var colorDisplay: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        //init targetView
        this.colorDisplay = findViewById(R.id.v_color_display)
        this.colorDisplay.setBackgroundColor(
            Color.rgb(
                DEFAULT_RAB_REPRESENTATION
                , DEFAULT_RAB_REPRESENTATION
                , DEFAULT_RAB_REPRESENTATION
            )
        )
        this.colorDisplay.alpha = DEFAULT_ALPHA_REPRESENTATION
        //init editTexts
        this.etRed = findViewById(R.id.et_red)
        this.etBlue = findViewById(R.id.et_blue)
        this.etGreen = findViewById(R.id.et_green)
        //init seekBars
        this.seekRed = findViewById(R.id.seek_red)
        this.seekBlue = findViewById(R.id.seek_blue)
        this.seekGreen = findViewById(R.id.seek_green)
        this.seekAlpha = findViewById(R.id.seek_alpha)
        //init textView
        this.tvAlpha = findViewById(R.id.tv_alpha)
        this.tvAlpha.text = "$DEFAULT_ALPHA_REPRESENTATION"
        this.tvColorHex = findViewById(R.id.color_hex)
        this.tvColorHex.text =
            String.format(hexFormat, seekRed.progress, seekGreen.progress, seekBlue.progress)
        //Attach listeners
        attachListeners()
    }

    private fun attachListeners() {
        //EditTextListeners
        this.etRed.addTextChangedListener(ColorsTextWatcher(etRed, seekRed))
        this.etGreen.addTextChangedListener(ColorsTextWatcher(etGreen, seekGreen))
        this.etBlue.addTextChangedListener(ColorsTextWatcher(etBlue, seekBlue))
        //seekBars listeners
        this.seekRed.setOnSeekBarChangeListener(this)
        this.seekGreen.setOnSeekBarChangeListener(this)
        this.seekBlue.setOnSeekBarChangeListener(this)
        this.seekAlpha.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar?.id) {
            R.id.seek_red -> etRed.setText("$progress")
            R.id.seek_green -> etGreen.setText("$progress")
            R.id.seek_blue -> etBlue.setText("$progress")
            R.id.seek_alpha -> tvAlpha.text = "${progress * 0.01}".substring(0, 3)
            else -> throw RuntimeException("Couldn't process seekBar id.")
        }
        this.colorDisplay.setBackgroundColor(
            Color.rgb(
                seekRed.progress,
                seekGreen.progress,
                seekBlue.progress
            )
        )
        this.colorDisplay.alpha = tvAlpha.text.toString().toFloat()
        this.tvColorHex.text =
            String.format(hexFormat, seekRed.progress, seekGreen.progress, seekBlue.progress)

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        //case seekBar indicator pressed
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        //case seekBar indicator released
    }
}