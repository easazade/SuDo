package ir.easazade.jesusnote.sdk

import io.reactivex.Scheduler
import kotlinx.coroutines.CoroutineDispatcher

class AppThreads(
  private val ioScheduler: Scheduler,
  private val uiScheduler: Scheduler,
  private val ioDispatcher: CoroutineDispatcher,
  private val uiDispatcher: CoroutineDispatcher
) : IAppThreads {

  override fun ioScheduler(): Scheduler = ioScheduler
  override fun uiScheduler(): Scheduler = uiScheduler
  override fun ioDispatcher(): CoroutineDispatcher = ioDispatcher
  override fun uiDispatcher(): CoroutineDispatcher = uiDispatcher
}