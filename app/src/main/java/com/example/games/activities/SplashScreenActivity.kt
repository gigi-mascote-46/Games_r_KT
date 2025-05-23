package com.example.games.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.games.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val textGameName = findViewById<TextView>(R.id.textGameName)

        val scaleAnimation = ScaleAnimation(
            0.0f, 1.0f,  // Escala de 0% até 100% em X
            0.0f, 1.0f,  // Escala de 0% até 100% em Y
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivô X no centro
            Animation.RELATIVE_TO_SELF, 0.5f   // Pivô Y no centro
        )
        scaleAnimation.duration = 1500
        scaleAnimation.fillAfter = true

        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // Iniciar LoginActivity após a animação
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish() // Encerra a splash para não voltar a ela
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        textGameName.startAnimation(scaleAnimation)
    }
}
