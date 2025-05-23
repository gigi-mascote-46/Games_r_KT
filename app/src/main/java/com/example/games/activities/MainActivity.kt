package com.example.games.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.games.R
import com.example.games.databinding.ActivityMainBinding
import com.example.games.games.CoinTossActivity
import com.example.games.games.DiceRollActivity
import com.example.games.games.GameManager
import com.example.games.games.HorseRaceActivity
import com.example.games.games.RussianRouletteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding e define o content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura o spinner com os jogos
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.jogos_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerJogos.adapter = adapter

        atualizarSaldo()

        // Listener para o botão de carregar saldo (ícone)
        binding.btnCarregarSaldo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Carregar Saldo")
                .setMessage(
                    "Use esta referência multibanco fictícia:\n\n" +
                            "Entidade: 12345\n" +
                            "Referência: 999 888 777\n" +
                            "Valor: à sua escolha"
                )
                .setPositiveButton("OK", null)
                .show()
        }

        // Listener para botão começar jogo
        binding.btnComecar.setOnClickListener {
            val jogoSelecionado = binding.spinnerJogos.selectedItem.toString()
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
        binding.textSaldo.text = "€${GameManager.balance}"
    }
}
