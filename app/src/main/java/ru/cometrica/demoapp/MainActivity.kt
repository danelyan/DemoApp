package ru.cometrica.demoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sum = BusinessLogic().plus(100, 200)
        Toast.makeText(this, "Sum == $sum", Toast.LENGTH_LONG)
            .show()
    }

}
