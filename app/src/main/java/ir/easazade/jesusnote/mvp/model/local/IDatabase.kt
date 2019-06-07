package ir.easazade.jesusnote.mvp.model.local

import ir.easazade.jesusnote.mvp.model.Task

interface IDatabase {

  fun saveTask(task: Task)
  fun deleteTask(taskId: Long)
}