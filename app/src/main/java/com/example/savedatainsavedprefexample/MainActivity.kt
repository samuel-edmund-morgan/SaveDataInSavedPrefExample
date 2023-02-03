package com.example.savedatainsavedprefexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.savedatainsavedprefexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //Create shared preference
        val sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

        //Create editor and make on click listener for button Save
        val editor = sharedPref.edit()
        activityMainBinding.saveBtn.setOnClickListener {
            editor.apply {
                putString("name", activityMainBinding.name.text.toString())
                putInt("age", activityMainBinding.age.text.toString().toInt())
                putBoolean("isAdult", activityMainBinding.isAdult.isChecked)
                apply()
            }
        }

        //Read from shared preferences to extract data and insert into text boxes
        activityMainBinding.loadBtn.setOnClickListener {
            activityMainBinding.apply {
                name.setText(sharedPref.getString("name", "null").toString())
                age.setText(sharedPref.getInt("age", 0).toString())
                isAdult.isChecked = sharedPref.getBoolean("isAdult", false)
            }
        }

    }
}