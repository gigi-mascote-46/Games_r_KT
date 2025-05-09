package com.example.gamemachine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamemachine.databinding.ActivityHorseRaceBinding
import kotlin.random.Random

class HorseRaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorseRaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorseRaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateBalanceDisplay()

        binding.btnCorrer.setOnClickListener {
            val betAmount = binding.etAposta.text.toString().toIntOrNull()
            val selectedHorse = binding.etCavalo.text.toString().toIntOrNull()

            if (betAmount == null || betAmount <= 0) {
                Toast.makeText(this, "Insira uma aposta válida!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedHorse == null || selectedHorse !in 1..3) {
                Toast.makeText(this, "Escolha um cavalo entre 1 e 3!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!GameManager.subtractBalance(betAmount)) {
                Toast.makeText(this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val winner = Random.nextInt(1, 4)
            val resultMessage = if (selectedHorse == winner) {
                GameManager.addBalance(betAmount * 3)
                "Parabéns! O cavalo $winner venceu! Ganhaste ${betAmount * 3}!"
            } else {
                "Perdeste! O cavalo $winner venceu."
            }

            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()
            updateBalanceDisplay()
        }
    }

    private fun updateBalanceDisplay() {
        binding.tvSaldo.text = "Saldo: €${GameManager.balance}"
    }
}
