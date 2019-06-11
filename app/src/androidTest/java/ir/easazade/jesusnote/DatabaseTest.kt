package ir.easazade.jesusnote

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.realm.Realm
import io.realm.RealmConfiguration
import ir.easazade.jesusnote.mvp.model.DbTask
import ir.easazade.jesusnote.mvp.model.local.AppDatabase
import ir.easazade.jesusnote.mvp.model.local.IAppDatabase
import ir.easazade.jesusnote.mvp.model.local.RealmProvider
import ir.easazade.jesusnote.utils.ListUtils.Companion.compareLists
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

  lateinit var database: IAppDatabase
  lateinit var fakes: FakeDataInstrumented

  init {
    Realm.init(InstrumentationRegistry.getInstrumentation().targetContext)
  }

  val realmProvider = object : RealmProvider {
    val config = RealmConfiguration.Builder()
      .name("test-realmProvider")
      .schemaVersion(10)
      .inMemory()
      .deleteRealmIfMigrationNeeded().build()

    override fun get(): Realm = Realm.getInstance(config)
  }

  @Before
  fun setup() {
    fakes = FakeDataInstrumented()
    database = AppDatabase(realmProvider)
  }

  @After
  fun tearDown() {
    realmProvider.get().executeTransaction { it.deleteAll() }
  }

  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("ir.easazade.jesusnote", appContext.packageName)
  }

  @Test
  fun androidTest_shouldSaveTask() {
    //assert first
    assertTrue(realmProvider.get().where(DbTask::class.java).findAll().isEmpty())
    //with
    val task = fakes.tasks[0]
    //when
    database.saveTask(task)
    //then
    assertTrue(realmProvider.get().where(DbTask::class.java).findAll().isNotEmpty())
  }

  @Test
  fun androidTest_shouldDeleteTask() {
    //assert first
    //with
    val task = fakes.tasks[0]
    database.saveTask(task)
    assertTrue(realmProvider.get().where(DbTask::class.java).equalTo("id", task.id).findAll().isNotEmpty())
    //when
    database.deleteTask(task.id)
    //then
    assertTrue(realmProvider.get().where(DbTask::class.java).equalTo("id", task.id).findAll().isEmpty())
  }

  @Test
  fun androidTest_shouldUpdateTask() {
    //assert first
    assertTrue(realmProvider.get().where(DbTask::class.java).findAll().isEmpty())
    //with
    val task = fakes.tasks[0]
    database.saveTask(task)
    assertTrue(realmProvider.get().where(DbTask::class.java).findAll().isNotEmpty())
    val newDesc = "some new description here and there"
    val updatedTask = task.copy(description = newDesc)
    //when
    database.saveTask(updatedTask)
    //then
    val result = database.getTask(updatedTask.id)!!
    assertEquals(result.description, newDesc)
  }

  @Test
  fun androidTest_shouldGetAllDays() {
    //with
    val days = fakes.days
    days.forEach { database.saveDay(it) }
    //when
    val result = database.getAllDays()
    //then
    assertEquals(days.size, result.size)
    val differences = compareLists(days, result) { d1, d2 -> d1.id == d2.id }
    differences.toList().forEach {
      assertTrue(it.isEmpty())
    }
  }
}