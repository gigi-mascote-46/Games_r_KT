package com.example.games.games

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.ActivityCoinTossBinding
import kotlin.random.Random

class CoinTossActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinTossBinding
    private val betAmount = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinTossBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateBalanceDisplay()

        binding.btnCarregarSaldo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Carregar Saldo")
                .setMessage(
                    "Use esta referência multibanco fictícia:\n\nEntidade: 12345\nReferência: 999 888 777\nValor: à sua escolha"
                )
                .setPositiveButton("OK", null)
                .show()
        }

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
        // Aqui o texto que aparece no topo, no layout, é textSaldo (não textBalance)
        binding.textSaldo.text = "€${GameManager.balance}"
    }
}
