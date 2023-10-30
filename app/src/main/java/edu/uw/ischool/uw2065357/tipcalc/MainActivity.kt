package edu.uw.ischool.uw2065357.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipButton = findViewById<Button>(R.id.tipButton)
        tipButton.isEnabled = false

        val inputAmountEditText: TextInputEditText = findViewById(R.id.input_amount)
        val currencyTextWatcher = CurrencyTextWatcher(inputAmountEditText, tipButton)
        inputAmountEditText.addTextChangedListener(currencyTextWatcher)

        val calculateTipButton = findViewById<Button>(R.id.tipButton)
        calculateTipButton.setOnClickListener {
            val inputText = inputAmountEditText.text.toString()
            val parsedAmount = parseAmount(inputText)

            if (parsedAmount > 0.0) {
                val tipAmount = parsedAmount * 0.15
                val formattedTipAmount = String.format("%.2f", tipAmount)
                val message = "You need to tip $$formattedTipAmount"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a valid amount.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun parseAmount(inputText: String): Double {
        // Parse the input text and return as a double
        return try {
            val cleanString = inputText.replace(Regex("[^\\d.]"), "")
            cleanString.toDouble()
        } catch (ex: NumberFormatException) {
            0.0
        }
    }
}
