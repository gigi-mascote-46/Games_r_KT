import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.games.GameManager
import com.example.games.databinding.ActivityHorseRaceBinding

class HorseRaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorseRaceBinding
    private val handler = Handler(Looper.getMainLooper())
    private var isRacing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorseRaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateBalanceDisplay()

        binding.btnCorrer.setOnClickListener {
            if (isRacing) return@setOnClickListener

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

            // Resetar progressos
            binding.pbHorse1.progress = 0
            binding.pbHorse2.progress = 0
            binding.pbHorse3.progress = 0
            isRacing = true

            startRace(betAmount, selectedHorse)
        }
    }

    private fun startRace(betAmount: Int, selectedHorse: Int) {
        val progresses = intArrayOf(0, 0, 0)

        val runnable = object : Runnable {
            override fun run() {
                for (i in progresses.indices) {
                    progresses[i] += Random.nextInt(1, 6)
                    if (progresses[i] > 100) progresses[i] = 100
                }

                // Atualizar UI
                binding.pbHorse1.progress = progresses[0]
                binding.pbHorse2.progress = progresses[1]
                binding.pbHorse3.progress = progresses[2]

                val winnerIndex = progresses.indexOfFirst { it >= 100 }
                if (winnerIndex != -1) {
                    isRacing = false
                    val winnerHorse = winnerIndex + 1
                    val message = if (selectedHorse == winnerHorse) {
                        GameManager.addBalance(betAmount * 3)
                        "🏆 O cavalo $winnerHorse venceu! Ganhaste ${betAmount * 3}!"
                    } else {
                        "💔 O cavalo $winnerHorse venceu. Perdeste!"
                    }

                    Toast.makeText(this@HorseRaceActivity, message, Toast.LENGTH_LONG).show()
                    updateBalanceDisplay()
                } else {
                    handler.postDelayed(this, 100)
                }
            }
        }

        handler.post(runnable)
    }

    private fun updateBalanceDisplay() {
        binding.tvSaldo.text = "Saldo: €${GameManager.balance}"
    }
}
