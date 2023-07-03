package com.mad.androidtraining.june14patterns

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.mad.androidtraining.R
import java.util.Calendar
import java.util.Date

class BasicFormActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_form)

        val edtTxtAge = findViewById<EditText>(R.id.edtTxtAge)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
           // edtTxtAge.setText("" + dayOfMonth + " " + monthOfYear + ", " + year)
        }, year, month, day)
        val txtLayoutAge = findViewById<TextInputLayout>(R.id.txtLayoutAge)
        txtLayoutAge.setEndIconOnClickListener {
            dpd.show()
        }

        val currentDate = Date()
        dpd.setOnDateSetListener { datePicker, i, i2, i3 ->
            val curAge  = year - i
            edtTxtAge.setText("$curAge")
        }



    }

}