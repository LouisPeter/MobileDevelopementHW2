package com.example.hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val barFragment = BarFragment()
        val lineFragment = LineFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fContainer, lineFragment)
            commit()
        }
        findViewById<Button>(R.id.btnBar).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fContainer, barFragment)
                commit()
            }
        }

        findViewById<Button>(R.id.btnLine).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fContainer, lineFragment)
                commit()
            }
        }
    }
}