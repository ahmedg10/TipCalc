package edu.uw.ischool.uw2065357.tipcalc
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class CurrencyTextWatcher(private val editText: TextInputEditText, private val tipButton: Button)
    : TextWatcher {
    private val decimalFormat: DecimalFormat =
        DecimalFormat("0.00", DecimalFormatSymbols.getInstance())

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        editText.removeTextChangedListener(this)
        try {
            val initialText = s.toString()
            val cleanString = initialText.replace(Regex("[^\\d]"), "")
            val parsed = cleanString.toDouble() / 100.0

            // Log the parsed value
            Log.d("CurrencyTextWatcher", "Parsed value: $parsed")

            val formatted = "$" + decimalFormat.format(parsed)
            editText.setText(formatted)
            editText.setSelection(formatted.length)

            // Enable the button when there's valid input
            tipButton.isEnabled = parsed > 0.0
        } catch (ex: NumberFormatException) {
            // Handle invalid input
        }
        editText.addTextChangedListener(this)
    }
}
