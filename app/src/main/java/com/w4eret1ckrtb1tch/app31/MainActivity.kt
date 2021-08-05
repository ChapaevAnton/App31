package com.w4eret1ckrtb1tch.app31

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button: Button = findViewById(R.id.button)
        val customView: CustomView = findViewById(R.id.custom_view)

        button.setOnClickListener {
            customView.setWidthStroke(50f)
            customView.setColorStroke(ContextCompat.getColor(this, R.color.green))
        }
    }
}