package ir.easazade.jesusnote

import io.mockk.*
import io.reactivex.schedulers.Schedulers
import ir.easazade.jesusnote.mvp.logic.HomePresenter
import ir.easazade.jesusnote.mvp.model.local.IAppDatabase
import ir.easazade.jesusnote.mvp.view.IHomeView
import ir.easazade.jesusnote.sdk.AppThreads
import ir.easazade.jesusnote.sdk.IAppThreads
import kotlinx.coroutines.asCoroutineDispatcher
import org.junit.*
import java.util.concurrent.Executor

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomePresenterUnitTest {

  private lateinit var database: IAppDatabase
  private lateinit var threads: IAppThreads
  private lateinit var view: IHomeView
  private lateinit var presenter: HomePresenter
  private lateinit var fakes: FakeData

  private fun createTrampoline(): IAppThreads {
    val trampoline = Executor { it.run() }
    return AppThreads(
      Schedulers.from(trampoline),
      Schedulers.from(trampoline),
      trampoline.asCoroutineDispatcher(),
      trampoline.asCoroutineDispatcher()
    )
  }

  @Before
  fun setup() {
    database = mockk(relaxUnitFun = true)
    threads = createTrampoline()
    view = mockk(relaxUnitFun = true)
    fakes = FakeData()
    presenter = HomePresenter(database, threads, view)
  }

  @After
  fun tearDown() {
    unmockkAll()
  }

  @Test
  fun test_initList_shouldGetAllDaysFromDatabaseAndPresentThemToTheUser() {
    //with
    val days = fakes.days.toMutableList()
    coEvery { database.getAllDays() } returns days
    //when
    presenter.initList()
    //then
    coVerify {
      view.initializeHomeList(days)
    }
  }
}
