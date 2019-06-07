package ir.easazade.jesusnote.sdk

import io.reactivex.Scheduler
import kotlinx.coroutines.CoroutineDispatcher

interface IAppThreads {

  fun ioScheduler(): Scheduler
  fun uiScheduler(): Scheduler
  fun ioDispatcher(): CoroutineDispatcher
  fun uiDispatcher(): CoroutineDispatcher
}