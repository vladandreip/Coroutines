package com.example.asyncflows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val numbersFlow = sendPrimes()
    println("Flow hasn't started yet")
    println("Starting flow now")
    numbersFlow.collect {
        println("Received: $it")
    }
    println("Received all primes")
}

fun sendPrimes() = flow {
    val primes = listOf(2,3,5,7,11,13,17,19,23,29)
    primes.forEach {
        delay(it * 100L)
        emit(it)
    }
}
