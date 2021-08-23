package com.example.asyncflows

import java.lang.Exception
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        onCompletionFunction()
    }
}

suspend fun tryCatch() {
    try {
        flowOf(1, 2, 3).onEach {
            check(it != 2)
        }.collect {
            println("Number: $it")
        }
    } catch (e: Exception) {
        println("Condition has not been met")
    }
}

//Catches exception before the catch block
suspend fun catchFunction() = flowOf(1, 2, 3)
    .onEach {
        check(it != 2)
    }
    .catch { e ->
        println("Condition has not been met")
    }.collect {
        println("Number: $it")
    }

//like the finally block in try-catch
suspend fun onCompletionFunction() = flowOf(1, 2, 3)
    .onEach {
        check(it != 2)
    }
    .onCompletion {
        if (it == null)
            println("completed successfully")
        else
            println("error occurred boy")

    }
    .catch { e ->
        println("Condition has not been met")
    }.collect {
        println("Number: $it")
    }