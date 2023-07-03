package com.mad.androidtraining.june14patterns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.mad.androidtraining.R
import java.lang.Exception

class PatternsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patterns)

        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val radioBtnPanildrome = findViewById<RadioButton>(R.id.radioBtnPanildrome)
        val radioBtnPattern1 = findViewById<RadioButton>(R.id.radioBtnPattern1)
        val radioBtnPattern2 = findViewById<RadioButton>(R.id.radioBtnPattern2)
        val radioBtnRevString = findViewById<RadioButton>(R.id.radioBtnRevString)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        val btnForm = findViewById<Button>(R.id.btnForm)
        btnForm.setOnClickListener {
            val i = Intent(this,BasicFormActivity::class.java)
            startActivity(i)
        }



        var resultString: String
        btnSubmit.setOnClickListener {
            resultString=""
            if(radioBtnPanildrome.isChecked){
                resultString = palindrome(editTextInput.text.toString().trim())

                val snack = Snackbar.make(it,resultString, Snackbar.LENGTH_LONG)
                snack.show()

            } else if (radioBtnPattern1.isChecked){
                resultString = pattern1(editTextInput.text.toString().trim())

            } else if (radioBtnPattern2.isChecked){
                resultString = pattern2(editTextInput.text.toString().trim())

            } else if (radioBtnRevString.isChecked){
                resultString = revString(editTextInput.text.toString().trim())

            }

            textViewResult.text = resultString
        }


    }

    private fun revString(string: String): String {
        var res=""

        for(i in string){
            res=i+res
        }
        return res
    }

    private fun pattern2(string: String): String {
        var res =""
        var n:Long=0

        try {
            n = string.toLong()
        } catch (e: Exception){
            Toast.makeText(this,"Check Input", Toast.LENGTH_SHORT).show()
        }
        if(n>10){
            return "Enter Less then 10"
        }

        for (i in 1..n){
            for(j in 1..i){
                res+= "$j "
            }
            res+="\n"
        }

        return res
    }

    private fun pattern1(string: String): String {
        var res =""

        var n:Long=0
        try {
            n = string.toLong()
        } catch (e: Exception){
            Toast.makeText(this,"Check Input", Toast.LENGTH_SHORT).show()
        }
        if(n>10){
            return "Enter Less then 10"
        }

        for (i in 1..n){
            for(j in i until n){
                res+=" "
            }
            for(k in 1 until (2*i)){
                res+= "$k "
            }
            res+="\n"
        }


        return res
    }

    private fun palindrome(string: String): String {
        var res =""
        res += if(string.reversed() == string){
            "Given String is Palindrome"
        } else {
            "Not a Palindrome"
        }
        Toast.makeText(this,res, Toast.LENGTH_SHORT).show()
        return res
    }
}