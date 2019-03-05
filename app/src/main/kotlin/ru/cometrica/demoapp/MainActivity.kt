package ru.cometrica.demoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.cometrica.demoapp.presentation.document.view.DocumentListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DocumentListFragment.newInstance(1))
            .commitNow()
    }

}
