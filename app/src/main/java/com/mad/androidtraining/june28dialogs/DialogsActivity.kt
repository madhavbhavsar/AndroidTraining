package com.mad.androidtraining.june28dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar
import com.mad.androidtraining.R
import java.util.Calendar

class DialogsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogs)

        val btnAlertDialog1 = findViewById<Button>(R.id.btnAlertDialog1)
        val btnAlertDialog2 = findViewById<Button>(R.id.btnAlertDialog2)
        val btnBottomSheet1 = findViewById<Button>(R.id.btnBottomSheet1)
        val btnBottomSheet2 = findViewById<Button>(R.id.btnBottomSheet2)

        btnAlertDialog1.setOnClickListener {
            alertDialog1(it)
        }
        btnAlertDialog2.setOnClickListener {
            alertDialog2(it)
        }
        btnBottomSheet1.setOnClickListener {
            bottomSheet1(it)
        }
        btnBottomSheet2.setOnClickListener {
            bottomSheet2(it)
        }




    }

    private fun bottomSheet2(view: View) {
        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(R.layout.layout_bottomsheet2)
        bottomSheet.behavior.peekHeight = 1000

        val txtDate = bottomSheet.findViewById<TextView>(R.id.txtDate)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            txtDate?.text = "$dayOfMonth / $monthOfYear / $year"
        }, year, month, day)
        txtDate?.setOnClickListener {
            dpd.show()
        }

        val txtTime = bottomSheet.findViewById<TextView>(R.id.txtTime)

        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, OnTimeSetListener { timePicker, i, i2 ->
            txtTime?.text = "$i : $i2"
        },hour,minute,false)

        txtTime?.setOnClickListener {
            mTimePicker.show()
        }

        bottomSheet.findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
            bottomSheet.dismiss()
        }

        bottomSheet.findViewById<Button>(R.id.btnEdit)?.setOnClickListener {
            Snackbar.make(view,"Edited Successfully", Snackbar.LENGTH_LONG).show()
        }

        val imgDr = bottomSheet.findViewById<ImageView>(R.id.imgDrProfile)
        if (imgDr != null) {
//            Glide.with(this)
//                .load("https://cdn.pixabay.com/photo/2017/03/31/17/44/avatar-2191934_1280.png")
//                .into(imgDr)
        }




        bottomSheet.show()
    }

    private fun bottomSheet1(view: View) {
        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(R.layout.activity_instagram)


        bottomSheet.behavior.peekHeight = 1000
        bottomSheet.show()
    }

    private fun alertDialog2(view: View) {

    }

    private fun alertDialog1(view : View) {

        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setTitle("This is Title")
        alertDialogBuilder.setMessage("This is message")

        alertDialogBuilder.setPositiveButton("Confirm", DialogInterface.OnClickListener { dialogInterface, i ->
            val snack = Snackbar.make(view,"This is a Snack", Snackbar.LENGTH_LONG).setAction("Action", null)
            snack.show()
        })




        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}