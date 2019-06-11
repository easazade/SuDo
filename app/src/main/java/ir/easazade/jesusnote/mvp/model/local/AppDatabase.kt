package ir.easazade.jesusnote.mvp.model.local

import io.realm.Realm
import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.DbDay
import ir.easazade.jesusnote.mvp.model.DbTask
import ir.easazade.jesusnote.mvp.model.Task

class AppDatabase(private val realmProvider: RealmProvider) : IAppDatabase {

  override fun saveTask(task: Task) {
    transaction { it.copyToRealmOrUpdate(task.toDbTask()) }
  }

  override fun getTask(taskId: Long): Task? =
    realmProvider.get().where(DbTask::class.java).equalTo("id", taskId).findFirst()?.toTask()

  override fun deleteTask(taskId: Long) {
    transaction { it.where(DbTask::class.java).findAll().deleteAllFromRealm() }
  }

  override fun getAllDays(): MutableList<Day> {
    val days = realmProvider.get().where(DbDay::class.java).findAll()
    return days.map { it.toDay() }.toMutableList()
  }

  override fun saveDay(day: Day) {
    transaction { it.copyToRealmOrUpdate(day.toDbDay()) }
  }

  private fun transaction(action: (Realm) -> Unit) {
    realmProvider.get().executeTransaction { realm -> action.invoke(realm) }
  }
}