package com.tp.travelpakistan.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ThrottleLatest(
    private val interval: Long = 300
) {
    private var debounceJob: Job? = null

    fun debounce(scope: CoroutineScope, operation: suspend ()->Unit){
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(interval)
            operation()
        }
    }
}

class ThrottleFirst(
    private val interval: Long = 300
){
    private var debounceJob: Job? = null

    fun debounce(scope: CoroutineScope, operation: suspend ()->Unit){
        if (debounceJob == null) {
            debounceJob = scope.launch {
                operation()
                delay(interval)
                debounceJob = null
            }
        }
    }
}

class ThrottleLatestThenFirst(
    private val interval: Long = 300
){
    val throttleFirst = ThrottleFirst()

    private var debounceJob: Job? = null

    fun debounce(scope: CoroutineScope, operation: suspend ()->Unit){
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(interval)
            throttleFirst.debounce(scope, operation)
        }
    }
}