package com.example.games.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.games.R
import com.example.games.databinding.ActivityLoginErradoBinding

private lateinit var binding: ActivityLoginErradoBinding

class LoginErradoActivity : AppCompatActivity() {
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
}
