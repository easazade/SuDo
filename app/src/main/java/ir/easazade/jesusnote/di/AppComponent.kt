package ir.easazade.jesusnote.di

import android.os.Handler
import android.os.Looper
import io.reactivex.schedulers.Schedulers
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.mvp.logic.HomePresenter
import ir.easazade.jesusnote.mvp.model.local.AppDatabase
import ir.easazade.jesusnote.mvp.model.local.IAppDatabase
import ir.easazade.jesusnote.mvp.view.IHomeView
import ir.easazade.jesusnote.sdk.AppThreads
import ir.easazade.jesusnote.sdk.BaseActivity
import ir.easazade.jesusnote.sdk.IAppThreads
import ir.easazade.jesusnote.sdk.Navigation
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppComponent(private val activity: BaseActivity) : IAppComponent {

  private var mDatabase: IAppDatabase? = null
  private var mAppThreads: IAppThreads? = null
  private var mNavigation: Navigation? = null

  override fun database(): IAppDatabase {
    return if (mDatabase != null)
      mDatabase!!
    else {
      mDatabase = AppDatabase()
      mDatabase!!
    }
  }

  override fun navigation(): Navigation {
    return if (mNavigation != null)
      mNavigation!!
    else {
      mNavigation = Navigation(activity.findViewById(R.id.FIRST_PLACE_HOLDER), activity)
      mNavigation!!
    }
  }

  override fun threads(): IAppThreads {
    return if (mAppThreads != null)
      mAppThreads!!
    else {
      val ioExecutor = Executors.newSingleThreadExecutor()
      val uiExecutor = object : Executor {
        private val mHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
          mHandler.post(command)
        }
      }
      mAppThreads = AppThreads(
        Schedulers.from(ioExecutor),
        Schedulers.from(uiExecutor),
        ioExecutor.asCoroutineDispatcher(),
        uiExecutor.asCoroutineDispatcher()
      )
      mAppThreads!!
    }
  }

  override fun newHomePresenter(homeView: IHomeView): HomePresenter =
      HomePresenter(database(),threads(),homeView)

}