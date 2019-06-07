package ir.easazade.jesusnote.model

import ir.easazade.jesusnote.utils.DateTime

data class Day(
  val dateTime: DateTime,
  val tasks: List<Task>
)

data class Task(
  val id: Long,
  val dateTime: DateTime,
  val description: String,
  val status: Boolean
)
