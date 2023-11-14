package com.example.bakemate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Link buttons
        // TODO(Link Recipies)
        val profileButton = findViewById<Button>(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val homeButton = findViewById<Button>(R.id.home_button)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val recipiesButton = findViewById<Button>(R.id.recipes_button)
        recipiesButton.setOnClickListener {
            //val intent = Intent (this, TODO(Add link to recipes page)::class.java)
            startActivity(intent)
        }
    }
}
