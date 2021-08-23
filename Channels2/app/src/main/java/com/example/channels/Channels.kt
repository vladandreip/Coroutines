package com.example.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    print("gyu")
    val channel = Channel<Int>()
    launch {
        (1..5).forEach {
            channel.send(it * it)
        }
    }

    for (i in 1..5) {
        println(channel.receive())
    }
}