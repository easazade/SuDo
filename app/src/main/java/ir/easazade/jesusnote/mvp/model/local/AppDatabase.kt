package ir.easazade.jesusnote.mvp.model.local

import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.Task

class AppDatabase : IAppDatabase {
  override fun saveTask(task: Task) {
  }

  override fun deleteTask(taskId: Long) {
  }

  override fun getAllDays(): MutableList<Day> {
  }
}