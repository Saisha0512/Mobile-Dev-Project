package com.example.campussyncigdtuw

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.content.Intent

class SignUpActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var mobile: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var signUpButton: Button
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize views
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        mobile = findViewById(R.id.mobile_no)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirm_password)
        signUpButton = findViewById(R.id.signup_button)
        backButton = findViewById(R.id.back_button)

        // Set click listener for the SignUp button
        signUpButton.setOnClickListener {
            val usernameInput = username.text.toString().trim()
            val emailInput = email.text.toString().trim()
            val mobileInput = mobile.text.toString().trim()
            val passwordInput = password.text.toString()
            val confirmPasswordInput = confirmPassword.text.toString()

            // Validation checks
            if (usernameInput.isEmpty()) {
                username.error = "Username is required"
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.error = "Invalid Email"
            } else if (mobileInput.isEmpty() || mobileInput.length != 10) {
                mobile.error = "Enter a valid 10-digit Mobile No."
            } else if (passwordInput.length < 6) {
                password.error = "Password must be at least 6 characters"
            } else if (passwordInput != confirmPasswordInput) {
                confirmPassword.error = "Passwords do not match"
            } else {
                // Save data to SharedPreferences
                val sharedPref = getSharedPreferences("CampusSyncPrefs", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", usernameInput)
                editor.putString("email", emailInput)
                editor.putString("mobile_no", mobileInput)
                editor.putString("password", passwordInput)
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                // Notify user and navigate to Dashboard
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        }

        // Back button click listener
        backButton.setOnClickListener {
            finish()
        }
    }
}