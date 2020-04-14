package ec.com.paul.arquitecturamvvm

import android.os.Handler
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton
import android.os.Looper


/**
 * Created by Paul Yaguachi on 17/10/2019.
 * Paul Local
 */
@Singleton
class AppExecutors(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {

    @Inject
    constructor() : this(
        Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.myLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}