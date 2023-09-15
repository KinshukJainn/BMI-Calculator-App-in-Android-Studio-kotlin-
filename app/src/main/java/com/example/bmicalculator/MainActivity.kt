package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weighttext = findViewById<EditText>(R.id.inputweight)
        val hieghttext = findViewById<EditText>(R.id.inputHieght)
        val calcbutton = findViewById<Button>(R.id.calcbtn)

        calcbutton.setOnClickListener {
            val weight = weighttext.text.toString()
            val height = hieghttext.text.toString()
            if(ValidateInput(weight , height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get result with 2 decimal places :
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
            }
        }
    }

    private fun ValidateInput(weight:String?,height:String?):Boolean{

        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty" ,Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){

        val reusltIndex = findViewById<TextView>(R.id.Answere)
        val resultDescription = findViewById<TextView>(R.id.result)
        val resultInfo = findViewById<TextView>(R.id.rangofbmi)

        reusltIndex.text = bmi.toString()
        resultInfo.text = "(Normal range is : 18.5 to 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = "Underweight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "Overweight"
                color = R.color.overweight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.Obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText
    }
}