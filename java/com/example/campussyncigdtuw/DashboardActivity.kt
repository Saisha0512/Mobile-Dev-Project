package com.example.campussyncigdtuw

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DashboardActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var helloTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Retrieve the username from SharedPreferences
        sharedPreferences = getSharedPreferences("CampusSyncPrefs", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User") // Default is "User" if no username found

        // Set the username in the TextView
        helloTextView = findViewById(R.id.hello_text)
        helloTextView.text = "Hello $username!"

        // Set up card click listeners
        val sgpaCalculatorCard = findViewById<CardView>(R.id.sgpaCalculatorCard)
        val noticeBoardCard = findViewById<CardView>(R.id.noticeBoardCard)
        val eventPlannerCard = findViewById<CardView>(R.id.eventPlannerCard)
        val communityChatsCard = findViewById<CardView>(R.id.communityChatsCard)

        sgpaCalculatorCard.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        noticeBoardCard.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        eventPlannerCard.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        communityChatsCard.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Profile button logic
        val profileButton = findViewById<ImageButton>(R.id.profile)
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            // Pass the username to ProfileActivity if needed
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}
