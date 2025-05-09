package com.example.gamemachine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamemachine.databinding.ActivityCointossBinding
import kotlin.random.Random

class CoinTossActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCointossBinding
    private val betAmount = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCointossBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateBalanceDisplay()

        binding.buttonBet.setOnClickListener {
            val result = if (Random.nextBoolean()) "Cara" else "Coroa"

            if (result == "Cara") {
                GameManager.addBalance(betAmount)
                binding.textResult.text = "Ganhaste! +$betAmount"
            } else {
                if (GameManager.subtractBalance(betAmount)) {
                    binding.textResult.text = "Perdeste! -$betAmount"
                } else {
                    Toast.makeText(this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show()
                    binding.textResult.text = ""
                    return@setOnClickListener
                }
            }

            updateBalanceDisplay()
        }
    }

    private fun updateBalanceDisplay() {
        binding.textBalance.text = "Saldo: ${GameManager.balance} moedas"
    }
}
