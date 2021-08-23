package com.example.asyncflows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    mapOperator().collect {
        println(it)
    }

    mapOperator2().collect {
        println(it)
    }

    println("Even numbers")
    filterOperator().collect {
        println(it)
    }

    transformOperator().collect {
        println(it)
    }

    println("Take operator")
    takeOperator().collect {
        println(it)
    }

    println("Terminal operators")
    println(reduceOperator())

    println("To list operator")
    println(toListOperator())

    println("Flow on operator - changes the context on which the flow is emitted")
    flowOnOperator().collect {
        println(it)
    }


}

private fun mapOperator() = (1..10).asFlow().map {
    delay(10)
    "I am a string now. Used to be: $it"
}

private fun mapOperator2() = flow {
    (10..20).forEach {
        emit(it)
    }
}.map {
    delay(10)
    "I am a string now. Was: $it"
}

private fun filterOperator() = flow {
    (1..10).forEach {
        emit(it)
    }
}.filter {
    it %2 == 0
}

private fun transformOperator() = flow {
    (1..10).forEach {
        emit(it)
    }
}.transform {
    emit(it)
    emit("Transforming this item in a string")
}

private fun takeOperator() = (5..10).asFlow()
    .take(2)

//terminalOperators
private suspend fun reduceOperator() = (1..10).asFlow().reduce { accumulator, value -> accumulator + value }

private suspend fun toListOperator() = (99..100).asFlow().toList()

private fun flowOnOperator() = flow {
    (200..205).forEach {
        delay(1000)
        println(Thread().name)
        emit(it)
    }
}.flowOn(Dispatchers.IO)