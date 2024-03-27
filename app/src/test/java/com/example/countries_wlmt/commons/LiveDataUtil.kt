package com.example.countries_wlmt.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

suspend fun <T> LiveData<T>.getOrAwaitLiveDataValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            // Remove the observer immediately after receiving the first value
            this@getOrAwaitLiveDataValue.removeObserver(this)
        }
    }
    withContext(Dispatchers.Main) {
        observeForever(observer)
    }

    // Wait for the LiveData to be set, with a delay using coroutines
    val startTime = System.currentTimeMillis()
    while (data == null) {
        // Check if the specified time has elapsed
        if (System.currentTimeMillis() - startTime >= timeUnit.toMillis(time)) {
            throw TimeoutException("LiveData value was never set.")
        }
        // Delay for a short period of time before checking again
        delay(10)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}