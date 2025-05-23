package com.example.games.games

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.ActivityDiceRollBinding
import com.example.games.R
import kotlin.random.Random

class DiceRollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiceRollBinding
    private val betAmount = 100
    private val diceImages = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiceRollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateBalanceDisplay()

        binding.buttonRoll.setOnClickListener {
            if (GameManager.balance < betAmount) {
                Toast.makeText(this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dice1 = Random.Default.nextInt(1, 7)
            val dice2 = Random.Default.nextInt(1, 7)
            val total = dice1 + dice2

            // Animar os dados antes de mostrar os resultados
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
            binding.imageDice1.startAnimation(shake)
            binding.imageDice2.startAnimation(shake)

            // Mostrar os dados
            binding.imageDice1.setImageResource(diceImages[dice1 - 1])
            binding.imageDice2.setImageResource(diceImages[dice2 - 1])

            if (total >= 8) {
                GameManager.addBalance(betAmount)
                binding.textResult.text = "Ganhaste! Soma: $total (+$betAmount)"
            } else {
                GameManager.subtractBalance(betAmount)
                binding.textResult.text = "Perdeste! Soma: $total (-$betAmount)"
            }

            updateBalanceDisplay()
        }
    }

    private fun updateBalanceDisplay() {
        binding.textBalance.text = "Saldo: ${GameManager.balance} moedas"
    }
}
