package com.example.games

class LoginOkActivity {

    // LoginOkActivity.kt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_ok)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Bem-vindo ao jogo!"
    }
}
