package com.example.gamemachine

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gamemachine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atualizar saldo assim que a atividade for criada
        updateBalanceDisplay()

        binding.btnCoinToss.setOnClickListener {
            startActivityWithAnimation(Intent(this, CoinTossActivity::class.java))
        }

        binding.btnHorseRace.setOnClickListener {
            startActivityWithAnimation(Intent(this, HorseRaceActivity::class.java))
        }

        binding.btnDiceRoll.setOnClickListener {
            startActivityWithAnimation(Intent(this, DiceRollActivity::class.java))
        }

        binding.btnRoulette.setOnClickListener {
            startActivityWithAnimation(Intent(this, RussianRouletteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualizar saldo sempre que a MainActivity voltar ao primeiro plano
        updateBalanceDisplay()
    }

    private fun updateBalanceDisplay() {
        binding.tvSaldo.text = "Saldo: €${GameManager.balance}"
    }

    // Função para iniciar a atividade com animação de transição
    private fun startActivityWithAnimation(intent: Intent) {
        val options = android.transition.TransitionInflater.from(this)
            .inflateTransition(android.R.transition.fade)

        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
