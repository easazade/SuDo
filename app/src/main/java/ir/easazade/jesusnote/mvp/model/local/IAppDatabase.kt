package ir.easazade.jesusnote.mvp.model.local

import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.Task

interface IAppDatabase {

  fun saveTask(task: Task)
  fun deleteTask(taskId: Long)
  fun getAllDays(): MutableList<Day>
}