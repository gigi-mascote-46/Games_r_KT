package com.example.games

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gamemachine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerJogos: Spinner
    private lateinit var textSaldo: TextView
    private lateinit var btnCarregarSaldo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerJogos = findViewById(R.id.spinnerJogos)
        textSaldo = findViewById(R.id.textSaldo)
        btnCarregarSaldo = findViewById(R.id.btnCarregarSaldo)

        val adapter = ArrayAdapter.createFromResource(this, R.array.jogos_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJogos.adapter = adapter

        atualizarSaldo()

        btnCarregarSaldo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Carregar Saldo")
                .setMessage("Use esta referência multibanco fictícia:\n\nEntidade: 12345\nReferência: 999 888 777\nValor: à sua escolha")
                .setPositiveButton("OK", null)
                .show()
        }

        findViewById<Button>(R.id.btnComecar).setOnClickListener {
            val jogoSelecionado = spinnerJogos.selectedItem.toString()
            val intent = when (jogoSelecionado) {
                "Coin Toss" -> Intent(this, CoinTossActivity::class.java)
                "Horse Race" -> Intent(this, HorseRaceActivity::class.java)
                "Dice Roll" -> Intent(this, DiceRollActivity::class.java)
                "Russian Roulette" -> Intent(this, RussianRouletteActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        atualizarSaldo()
    }

    private fun atualizarSaldo() {
        textSaldo.text = "€${GameManager.balance}"
    }
}
