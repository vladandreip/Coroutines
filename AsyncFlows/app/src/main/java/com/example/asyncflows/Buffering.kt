package com.example.asyncflows

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            generate()
                .buffer()
                .collect {
                //consuming a flow takes more than the emission => performance bottleneck
                delay(300)
                println(it)
            }
        }

        println("Collected in $time")
    }
}

fun generate() = flow {
    (1..3).forEach {
        //time of 100 mills to emit a flow
        delay(100)
        println("emit")
        emit(it)
    }
}
