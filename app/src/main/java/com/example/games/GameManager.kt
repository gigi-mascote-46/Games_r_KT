package com.example.gamemachine

object GameManager {
    var balance: Int = 100

    fun addBalance(amount: Int) {
        balance += amount
    }

    fun subtractBalance(amount: Int): Boolean {
        return if (balance >= amount) {
            balance -= amount
            true
        } else {
            false
        }
    }
}
