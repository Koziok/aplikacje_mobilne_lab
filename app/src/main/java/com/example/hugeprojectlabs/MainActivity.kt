package com.example.hugeprojectlabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = DatabaseHelper(applicationContext)

        //val text_view = findViewById<TextView>(R.id.test)

        supportFragmentManager!!.
        beginTransaction().
        add(R.id.fragmentContainer, ListFragment(), "ListFragment").commit()


    }
}