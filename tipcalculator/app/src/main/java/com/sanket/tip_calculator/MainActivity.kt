package com.sanket.tip_calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.tip_calculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServices.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipAmount.text = ""
            return
        }
        val tipPercentage = when (binding.serviceRating.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_fifteen_percent -> 0.15
            else -> 0.10
        }
        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = ceil(tip)
        }
        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formatTip)
    }

}