package ec.com.paul.arquitecturamvvm.utils

import android.util.ArrayMap
import java.util.concurrent.TimeUnit
import android.os.SystemClock



/**
 * Created by Paul Yaguachi on 18/10/2019.
 * Paul Local
 */
class RateLimiter<KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = ArrayMap<KEY, Long>()
    private val timeout: Long

    init {
        this.timeout = timeUnit.toMillis(timeout.toLong())
    }

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = timestamps[key]
        val now = now()
        if (lastFetched == null) {
            timestamps[key] = now
            return true
        }

        if (now - lastFetched > timeout) {
            timestamps[key] = now
            return true
        }

        return false
    }

    private fun now(): Long {
        return SystemClock.uptimeMillis()
    }

    @Synchronized
    fun reset(key: KEY) {
        timestamps.remove(key)
    }
}
