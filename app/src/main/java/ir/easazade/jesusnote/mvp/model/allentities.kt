package ir.easazade.jesusnote.mvp.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ir.easazade.jesusnote.utils.DateTime
import java.sql.Timestamp

data class Day(
  val id: Long,
  val dateTime: DateTime,
  val tasks: List<Task>
) {

  fun toDbDay() = DbDay(id, dateTime.timestamp.time, tasks.map { it.toDbTask() }.toRealmList())
}

private fun <E> List<E>.toRealmList(): RealmList<E> = RealmList<E>().also {
  it.addAll(this)
}

data class Task(
  val id: Long,
  val dateTime: DateTime,
  val description: String,
  val status: Boolean
) {

  fun toDbTask() = DbTask(id, dateTime.timestamp.time, description, status)
}

//############################################# DB Entities ###########################################33

open class DbDay(
  @PrimaryKey
  var id: Long = 0,
  var dataTime: Long = 0,
  var tasks: RealmList<DbTask> = RealmList()
) : RealmObject() {

  fun toDay() = Day(id, DateTime(Timestamp(dataTime)), tasks.map { it.toTask() })
}

open class DbTask(
  @PrimaryKey
  var id: Long = 0,
  var dateTime: Long = 0,
  var description: String = "",
  var status: Boolean = false
) : RealmObject() {

  fun toTask(): Task = Task(id, DateTime(Timestamp(dateTime)), description, status)
}

