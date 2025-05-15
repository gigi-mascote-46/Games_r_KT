package com.example.games
// This file is part of the Exercicio7 project.


// It is subject to the license terms in the LICENSE file found in the top-level directory of this distribution.
// No part of the Exercicio7 project, including this file, may be copied, modified, propagated, or distributed except according to the terms contained in the LICENSE file.


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

// This is the SplashActivity class that displays a splash screen for 3 seconds before navigating to the MainActivity.
// It extends AppCompatActivity to provide compatibility support for older Android versions.
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },4000) // 4 segundos
    }
}
