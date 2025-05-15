package com.example.games

object GameManager {
    var balance: Int = 1000

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
