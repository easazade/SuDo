package ir.easazade.jesusnote.mvp.logic

import ir.easazade.jesusnote.mvp.model.Task
import ir.easazade.jesusnote.mvp.model.local.IAppDatabase
import ir.easazade.jesusnote.mvp.view.IHomeView
import ir.easazade.jesusnote.sdk.IAppThreads
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HomePresenter(
  private val database: IAppDatabase,
  private val threads: IAppThreads,
  private val view: IHomeView
) : BasePresenter {

  private val job = Job()

  fun saveTask(task: Task) {
  }

  fun deleteTask(taskId: Long) {

  }

  fun addNewTask(task:Task){

  }

  fun initList() = launch {
    val days = withContext(threads.ioDispatcher()) { database.getAllDays() }
    view.initializeHomeList(days)
  }

  override val coroutineContext: CoroutineContext
    get() = threads.uiDispatcher() + job

  override fun clearSubscriptions() {
    job.cancel()
  }
}