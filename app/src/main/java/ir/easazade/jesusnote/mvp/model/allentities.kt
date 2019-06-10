package ir.easazade.jesusnote.mvp.model

import io.realm.RealmObject
import ir.easazade.jesusnote.utils.DateTime

data class Day(
  val id: Long,
  val dateTime: DateTime,
  val tasks: List<Task>
)

data class Task(
  val id: Long,
  val dateTime: DateTime,
  val description: String,
  val status: Boolean
)

//############################################# DB Entities ###########################################33

data class DbDay(
  val id: Long,
  val dataTime: Long,
  val tasks: List<DbTask>
) : RealmObject()

data class DbTask(
  val id: Long,
  val dateTime: Long,
  val description: String,
  val status: Boolean
) : RealmObject()

