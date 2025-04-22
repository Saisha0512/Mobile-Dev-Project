package com.example.campussyncigdtuw

import android.os.Bundle
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.widget.ProgressBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        // Create a pulse animation for the ProgressBar
        val animator = ObjectAnimator.ofFloat(progressBar, "scaleX", 1f, 1.5f, 1f)
        animator.duration = 1000 // 1 second for each cycle
        animator.repeatCount = ValueAnimator.INFINITE // Loop infinitely
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()


        // Navigate to MainActivity after 3 seconds
        progressBar.postDelayed({
            val sharedPref = getSharedPreferences("CampusSyncPrefs", MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

            if (isLoggedIn) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        },3000)
    }
}