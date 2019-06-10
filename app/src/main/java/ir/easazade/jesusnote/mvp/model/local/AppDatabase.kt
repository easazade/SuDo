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

  override fun deleteTask(taskId: Long) {
    transaction { it.where(DbTask::class.java).findAll().deleteAllFromRealm() }
  }

  override fun getAllDays(): MutableList<Day> {
    val days = realmProvider.get().where(DbDay::class.java).findAll()
    return days.map { it.toDay() }.toMutableList()
  }

  private fun transaction(action: (Realm) -> Unit) {
    realmProvider.get().executeTransaction { realm -> action.invoke(realm) }
  }
}