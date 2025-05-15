package com.example.games

import android.os.Bundle

class LoginErradoActivity{
// LoginErradoActivity.kt
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login_errado)

    val textView = findViewById<TextView>(R.id.textView)
    val btnBack = findViewById<Button>(R.id.btnBack)

    textView.text = "Login errado. Tente novamente."

    btnBack.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
