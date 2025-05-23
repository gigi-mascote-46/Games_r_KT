package com.example.games.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.ActivityRegisterBinding
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var birthDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etBirthdate.setOnClickListener {
            showDatePicker()
        }

        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                saveUserData()
                Toast.makeText(this, "Registo completo!", Toast.LENGTH_SHORT).show()
                finish() // Voltar ao ecrã anterior (Login)
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(this,
            { _, year, month, dayOfMonth ->
                birthDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                val dateFormatted = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .format(birthDate!!.time)
                binding.etBirthdate.setText(dateFormatted)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun validateForm(): Boolean {
        val name = binding.etFirstName.text.toString().trim()
        val surname = binding.etLastName.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val acceptedTerms = binding.cbTerms.isChecked

        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Preenche todos os campos!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (birthDate == null) {
            Toast.makeText(this, "Escolhe a data de nascimento!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!isAtLeast18YearsOld(birthDate!!)) {
            Toast.makeText(this, "Tens de ter pelo menos 18 anos!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!acceptedTerms) {
            Toast.makeText(this, "Tens de aceitar os termos e condições!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun isAtLeast18YearsOld(birth: Calendar): Boolean {
        val today = Calendar.getInstance()
        val eighteenYearsAgo = Calendar.getInstance().apply {
            add(Calendar.YEAR, -18)
        }
        return birth.before(eighteenYearsAgo) || birth == eighteenYearsAgo
    }

    private fun saveUserData() {
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        with(prefs.edit()) {
            putString("firstName", binding.etFirstName.text.toString().trim())
            putString("lastName", binding.etLastName.text.toString().trim())
            putString("username", binding.etUsername.text.toString().trim())
            putString("email", binding.etEmail.text.toString().trim())
            putString("birthdate", binding.etBirthdate.text.toString().trim())
            apply()
        }
    }
}
