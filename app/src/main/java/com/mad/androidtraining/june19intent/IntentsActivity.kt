package com.mad.androidtraining.june19intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.mad.androidtraining.R
import java.net.MalformedURLException
import java.net.URL
import java.util.regex.Pattern


class IntentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)

        val edtMobile = findViewById<EditText>(R.id.edtTxtMobile)
        val edtEmail = findViewById<EditText>(R.id.edtTxtEmail)
        val edtSite = findViewById<EditText>(R.id.edtTxtSite)

        val txtInpMobile = findViewById<TextInputLayout>(R.id.txtInpMobile)
        val txtInpEmail = findViewById<TextInputLayout>(R.id.txtInpEmail)
        val txtInpSite = findViewById<TextInputLayout>(R.id.txtInpSite)
        val txtInpCam = findViewById<TextInputLayout>(R.id.txtInpCam)

        txtInpMobile.setEndIconOnClickListener {
            val url = edtMobile.text.toString()

            if (validatePhoneNumber(url)) {
                val urlIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$url"))
                startActivity(urlIntent)
            } else {
                Toast.makeText(this, "Mobile incorrect", Toast.LENGTH_SHORT).show()

            }

        }

        txtInpEmail.setEndIconOnClickListener {
            val url = edtEmail.text.toString()
            if (validateEmail(url)) {
                val urlIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$url"))
                startActivity(urlIntent)
            } else {
                Toast.makeText(this, "Email incorrect", Toast.LENGTH_SHORT).show()
            }

        }
        txtInpSite.setEndIconOnClickListener {
            val url = "https://" + edtSite.text.toString()

            if (validateWebsite(url)) {
                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(urlIntent)
            } else {
                Toast.makeText(this, "Website incorrect", Toast.LENGTH_SHORT).show()
            }
        }

        txtInpCam.setEndIconOnClickListener {
            val urlIntent = Intent(Intent.ACTION_CAMERA_BUTTON)
            startActivity(urlIntent)
        }

        val btnExplicit = findViewById<Button>(R.id.btnExplicit)
        btnExplicit.setOnClickListener {
            val i = Intent(this, IntentsSecondActivity::class.java)
            i.putExtra("Email", edtEmail.text.toString())
            i.putExtra("Mobile", edtMobile.text.toString())
            i.putExtra("Site", edtSite.text.toString())
            startActivity(i)


        }


        val inpTxt = findViewById<EditText>(R.id.edtTxt)
        val click = findViewById<Button>(R.id.btn)

        click.setOnClickListener {
            val url: String = inpTxt.text.toString()

            if (validatePhoneNumber(url)) {
                val urlIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$url"))
                startActivity(urlIntent)
            } else if (validateEmail(url)) {

                val urlIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$url"))
                startActivity(urlIntent)


            } else if (validateWebsite("https://$url")) {

                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
                startActivity(urlIntent)

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun validateEmail(url: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$")
        val matcher = pattern.matcher(url)
        return matcher.matches()
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        val pattern = Pattern.compile("^[+]?[0-9]{10,13}$")
        val matcher = pattern.matcher(phoneNumber)
        return matcher.matches()
    }

    private fun validateWebsite(website: String): Boolean {

        Log.i("tahherer",website)
        try {
            val url = URL(website)
            return URLUtil.isValidUrl(website) && Patterns.WEB_URL.matcher(website).matches()
        } catch (ignored: MalformedURLException) {
        }
        return false
    }


}