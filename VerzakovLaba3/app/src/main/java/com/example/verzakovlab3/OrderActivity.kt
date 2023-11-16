package com.example.verzakovlab3

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        // Получаем выбранный инструмент из SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val textView10Value = sharedPreferences.getInt("textView10Value", 0)
        val textView5Value = sharedPreferences.getInt("textView5Value", 0)
        val selectedInstrument = sharedPreferences.getString("selectedInstrument", "")

        // Отображаем выбранный инструмент в textView8
        val textView8: TextView = findViewById(R.id.textView8)
        textView8.text = selectedInstrument

        val chelikName = sharedPreferences.getString("chelikName", "")

        // Отображаем имя в TextView с id name_of_zakz
        val nameOfZakz: TextView = findViewById(R.id.name_of_zakz)
        nameOfZakz.text = chelikName

        val colVo: TextView = findViewById(R.id.col_vo)
        colVo.text = textView10Value.toString()

        val cena: TextView = findViewById(R.id.cena)
        cena.text = textView5Value.toString()

        val button2: Button = findViewById(R.id.button2)

        button2.setOnClickListener {
            // Создаем интент для отправки email
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("sasadevatovskij@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Заказ с MusicShop")

            // Заполняем тело сообщения
            val textView8 = findViewById<TextView>(R.id.textView8)
            val colVo = findViewById<TextView>(R.id.col_vo)
            val cena = findViewById<TextView>(R.id.cena)
            val name = findViewById<TextView>(R.id.name_of_zakz)

            val messageBody = "Имя заказчика: ${name.text}\n" +
                    "Товар в корзине: ${textView8.text}\n"+
                    "Количество товара: ${colVo.text}\n" +
                    "Цена заказа: ${cena.text}"

            intent.putExtra(Intent.EXTRA_TEXT, messageBody)

            // Проверяем, есть ли приложение Gmail на устройстве
            val packageManager = packageManager
            val activities = packageManager.queryIntentActivities(intent, 0)
            val isIntentSafe = activities.isNotEmpty()

            // Если есть приложение Gmail, запускаем его
            if (isIntentSafe) {
                startActivity(Intent.createChooser(intent, "Выберите приложение для отправки"))
            } else {
                // Если приложение Gmail отсутствует, вы можете обработать это событие соответствующим образом
                // например, выведите сообщение об отсутствии почтового клиента
            }
        }
    }
}