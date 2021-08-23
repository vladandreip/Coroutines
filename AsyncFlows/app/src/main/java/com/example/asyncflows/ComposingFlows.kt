package com.example.asyncflows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        zip()
        combine()
    }
}

suspend fun zip() {
    val flow1 = flowOf("un", "dos", "tres")
    val flow2 = flowOf("jap", "jap", "jap")
    flow1.zip(flow2) { a, b ->
        "$a $b"
    }.collect {
        println(it)
    }
}

suspend fun combine() {
    val flow1 = flowOf("unu", "trei", "cinci").onEach { delay(100) }
    val flow2 = flowOf("doi", "patru", "sase").onEach { delay(300) }
    flow1.combine(flow2) { a, b ->
        "$a $b"
    }.collect {
        println(it)
    }
}

