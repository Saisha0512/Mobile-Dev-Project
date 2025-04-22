package com.example.campussyncigdtuw

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fullNameTextView: TextView // Declare once here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("CampusSyncPrefs", Context.MODE_PRIVATE)

        // UI elements
        val backButton: ImageButton = findViewById(R.id.back_button)
        val updateButton: Button = findViewById(R.id.update_button)
        val usernameEditText: EditText = findViewById(R.id.username)
        val emailEditText: EditText = findViewById(R.id.email)
        val mobileEditText: EditText = findViewById(R.id.mobile_no)
        val passwordEditText: EditText = findViewById(R.id.password)
        fullNameTextView = findViewById(R.id.full_name) // Initialize here

        // Fetch saved data from SharedPreferences
        val savedUsername = sharedPreferences.getString("username", "") // Use correct key (case-sensitive)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val savedMobile = sharedPreferences.getString("mobile_no", "")

        // Populate fields from SharedPreferences
        usernameEditText.setText(savedUsername)
        emailEditText.setText(savedEmail)
        passwordEditText.setText(savedPassword)
        mobileEditText.setText(savedMobile)

        // Set the username in the TextView
        fullNameTextView.text = "$savedUsername"

        // Back button listener to go back to the previous screen
        backButton.setOnClickListener {
            finish()  // Close the activity and return to the previous screen
        }

        // Update button listener to save new profile details
        updateButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val mobile = mobileEditText.text.toString().trim()

            // Validate email format
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid Email Format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if fields are not empty
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Save the updated information into SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username) // Ensure consistent key
                editor.putString("EMAIL", email)
                editor.putString("PASSWORD", password)
                editor.putString("MOBILE", mobile)  // Save the mobile number
                editor.apply()

                // Notify user that the profile has been updated
                Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show()

                // Update the name displayed at the top
                fullNameTextView.text = "Name : $username!"
            } else {
                // Show an error message if any of the mandatory fields are empty
                Toast.makeText(this, "Please fill Username, Email, and Password!", Toast.LENGTH_SHORT).show()
            }
        }

        // Logout button listener to clear SharedPreferences and redirect to Login screen
        val logoutButton: Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            // Redirect to the login screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // End current activity
        }
    }
}