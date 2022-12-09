package com.programmingwithrajeev.agetominute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelected_Date: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelected_Date = findViewById(R.id.Selected_date)
        tvAgeInMinutes = findViewById(R.id.age_in_minutes)
        val btnSelectDate: Button = findViewById(R.id.btn_select_Date)
        btnSelectDate.setOnClickListener {
            onSelectedDate()
        }
    }

    private fun onSelectedDate() {
        // to get current date
        val myCal = Calendar.getInstance()
        val cYear = myCal.get(Calendar.YEAR)
        val cMonth = myCal.get(Calendar.MONTH)
        val cDays = myCal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { _, year, month, dayofmonth ->
                Toast.makeText(this, "year - $year month - ${month + 1} Day - $dayofmonth ", Toast.LENGTH_LONG).show()
                // age = current = DOB
                // In minutes
                // step1 calculate the current date 27/09/22
                val selectedDate = "$dayofmonth/${month+1}/$year"
                // step2 show the selected date
                tvSelected_Date?.text = selectedDate

                // india time zone
                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val bDate = sdf.parse(selectedDate)
                bDate?.let {
                    val bDateInMin = bDate.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currDateInMinutes = currentDate.time/60000
                        val ageInMinutes = currDateInMinutes - bDateInMin
                        tvAgeInMinutes?.text = ageInMinutes.toString()
                        }
                    }

            },
            cYear, cMonth, cDays)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 8460000
        dpd.show()

    }
}