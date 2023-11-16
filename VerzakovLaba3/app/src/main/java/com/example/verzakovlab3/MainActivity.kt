package com.example.verzakovlab3

import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView10: TextView
    private lateinit var textView5: TextView
    private lateinit var instrumentSpinner: Spinner
    private lateinit var imageView5: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView10 = findViewById(R.id.textView10)
        textView5 = findViewById(R.id.textView5)
        instrumentSpinner = findViewById(R.id.instrumentSpinner)
        imageView5 = findViewById(R.id.imageView5)

        val plusButton: Button = findViewById(R.id.plusButton)
        val minusButton: Button = findViewById(R.id.minusButton)
        val button: Button = findViewById(R.id.button)
        val editTextChelikName: EditText = findViewById(R.id.chelik_name)

        button.setOnClickListener {
            // Открываем активность OrderActivity
            val chelikName = editTextChelikName.text.toString()
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("chelikName", chelikName)
            editor.apply()

            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        val instruments = resources.getStringArray(R.array.instruments)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, instruments)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        instrumentSpinner.adapter = adapter

        instrumentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Обнуляем значения textView5 и textView10 при смене инструмента
                textView5.text = "0"
                textView10.text = "0"

                val selectedInstrument = instruments[position]
                when (selectedInstrument) {
                    "guitar" -> {
                        plusAmount = 3
                        minusAmount = 3
                        imageView5.setImageResource(R.drawable.guitar_image)
                    }
                    "drum" -> {
                        plusAmount = 2
                        minusAmount = 2
                        imageView5.setImageResource(R.drawable.drum_image)
                    }
                    "keyboard" -> {
                        plusAmount = 1
                        minusAmount = 1
                        imageView5.setImageResource(R.drawable.keyboard_image)
                    }
                }

                // Сохраняем выбранный инструмент в SharedPreferences
                val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("selectedInstrument", selectedInstrument)
                editor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Ничего не делаем при отсутствии выбора
            }
        }

        plusButton.setOnClickListener {
            val currentValue10 = textView10.text.toString().toInt()
            textView10.text = (currentValue10 + 1).toString()

            val currentValue5 = textView5.text.toString().toInt()
            textView5.text = (currentValue5 + plusAmount).toString()

            // Сохраняем обновленные значения textView10 и textView5 в SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("textView10Value", currentValue10 + 1)
            editor.putInt("textView5Value", currentValue5 + plusAmount)
            editor.apply()
        }

        minusButton.setOnClickListener {
            val currentValue10 = textView10.text.toString().toInt()
            if (currentValue10 > 0) {
                textView10.text = (currentValue10 - 1).toString()
            }

            val currentValue5 = textView5.text.toString().toInt()
            if (currentValue5 >= minusAmount) {
                textView5.text = (currentValue5 - minusAmount).toString()
            }
            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("textView10Value", currentValue10 - 1)
            editor.putInt("textView5Value", currentValue5 - minusAmount)
            editor.apply()
        }
    }

    private var plusAmount = 1
    private var minusAmount = 1
}