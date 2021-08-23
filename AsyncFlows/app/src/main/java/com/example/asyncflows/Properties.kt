package com.example.asyncflows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main(): Unit = runBlocking {
    withTimeoutOrNull(1000) {
        sendNumbersTimeout().collect {
            println(it)
        }
    }
}

private fun sendNumbersTimeout() = flow {
    listOf(1,2,3).forEach {
        delay(400)
        emit(it)
    }
}