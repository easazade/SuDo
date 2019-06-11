package ir.easazade.jesusnote.di

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import ir.easazade.jesusnote.BuildConfig
import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.Task
import ir.easazade.jesusnote.mvp.model.local.AppDatabase
import ir.easazade.jesusnote.mvp.model.local.RealmProvider
import ir.easazade.jesusnote.utils.DateTime
import ir.easazade.jesusnote.utils.currentDateTime
import java.sql.Timestamp
import kotlin.random.Random

class AppDatabaseModule(app: Application) : IDatabaseModule {

  private var database: AppDatabase? = null

  init {
    Realm.init(app)
    val realmConfig = RealmConfiguration.Builder()
      .name("sudo_app_database")
      .initialData { realm ->
        if (BuildConfig.DEBUG) {
          val tasks = mutableListOf(
            Task(0, currentDateTime(), "یک روز خوب و بارونی که می شه خیلی خوب و عالی زندگی کرد", false),
            Task(1, currentDateTime(), "رضا اشبلان", true),
            Task(2, currentDateTime(), "خرید خونه", true),
            Task(3, currentDateTime(), "خرید لباس", false),
            Task(4, currentDateTime(), "برم پیش مهدی", false),
            Task(5, currentDateTime(), "برم عروسی", false),
            Task(6, currentDateTime(), "خواب", false),
            Task(7, currentDateTime(), "ورزش", false),
            Task(8, currentDateTime(), "برم شرکت", false)
          )

          var taskId = 1L
          val rand = Random(5)

          val days = listOf(
            Day(0, DateTime(Timestamp.valueOf("2019-06-25 20:50:00")), tasks),
            Day(1, DateTime(Timestamp.valueOf("2019-06-24 20:50:00")), tasks),
            Day(2, DateTime(Timestamp.valueOf("2019-06-23 20:50:30")), tasks),
            Day(3, DateTime(Timestamp.valueOf("2019-06-22 20:50:42")), tasks),
            Day(4, DateTime(Timestamp.valueOf("2019-06-21 20:50:00")), tasks),
            Day(5, DateTime(Timestamp.valueOf("2019-06-20 20:50:00")), tasks)
          ).map { oldDay ->
            oldDay.copy(tasks = oldDay.tasks.map { oldTask ->
              oldTask.copy(dateTime = oldDay.dateTime)
            })
          }.map { oldDay ->
            val index = 3 + rand.nextLong(5)
            oldDay.copy(tasks = oldDay.tasks.filter { task ->
              task.id < index
            })
          }.map { day ->
            day.copy(tasks = day.tasks.map { task ->
              task.copy(id = taskId++)
            })
          }

          //inserting days and tasks
          realm.copyToRealmOrUpdate(days.map { it.toDbDay() })
        }
      }
      .schemaVersion(2)
      .deleteRealmIfMigrationNeeded().build()
    Realm.setDefaultConfiguration(realmConfig)
  }

  override fun realmProvider(): RealmProvider = object : RealmProvider {
    override fun get(): Realm = Realm.getDefaultInstance()
  }

  override fun database(): AppDatabase =
    if (database != null)
      database!!
    else {
      database = AppDatabase(realmProvider())
      database!!
    }
}