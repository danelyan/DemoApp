package ru.cometrica.demoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sum = BusinessLogic().plus(a, b)
        Toast.makeText(this, "Sum == $sum", Toast.LENGTH_LONG)
            .show()

        button.setOnClickListener {
            textView.text = getString(R.string.clicked_text_state)
            it.isEnabled = false
        }
    }

    companion object {
        const val a = 100
        const val b = 200
    }

}
