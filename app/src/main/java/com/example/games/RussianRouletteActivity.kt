package com.example.gamemachine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gamemachine.databinding.ActivityRussianRouletteBinding
import kotlin.random.Random

class RussianRouletteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRussianRouletteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRussianRouletteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atualiza o saldo na interface
        updateBalanceDisplay()

        // Ação para o botão girar
        binding.spinButton.setOnClickListener {
            spinWheel()
        }
    }

    // Função para atualizar o saldo exibido
    private fun updateBalanceDisplay() {
        binding.balanceTextView.text = "Saldo: €${GameManager.balance}"  // Usando GameManager para saldo
    }

    // Função que simula o giro do tambor e o jogo
    private fun spinWheel() {
        val bulletPosition = Random.nextInt(1, 7)  // Posicionamento de 1 a 6
        val playerPick = Random.nextInt(1, 7)  // O jogador escolhe um número entre 1 e 6

        // Verifica se o jogador "acertou" (ou seja, se a bala estava na posição sorteada)
        if (bulletPosition == playerPick) {
            binding.resultTextView.text = "Bang! Você perdeu!"
            GameManager.subtractBalance(100)  // Usando GameManager para subtrair saldo
            Toast.makeText(this, "Você perdeu 100!", Toast.LENGTH_SHORT).show()
        } else {
            binding.resultTextView.text = "Ufa! Não havia bala. Você sobreviveu!"
            GameManager.addBalance(50)  // Usando GameManager para adicionar saldo
            Toast.makeText(this, "Você ganhou 50!", Toast.LENGTH_SHORT).show()
        }

        // Atualiza o saldo após o jogo
        updateBalanceDisplay()
    }
}
