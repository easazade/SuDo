package ir.easazade.jesusnote

import android.app.Application
import android.content.Context
import ir.easazade.jesusnote.di.IAppComponent
import ir.easazade.jesusnote.sdk.BaseActivity
import ir.easazade.jesusnote.utils.AppContextWrapper
import timber.log.Timber
import timber.log.Timber.DebugTree

fun injection() = App.component()

class App : Application() {

  companion object {
    private var mComponent: IAppComponent? = null
    fun component() = mComponent!!
    fun get(activity: BaseActivity): App = activity.application as App
  }

  fun isAppComponentSet() = mComponent != null

  fun setAppComponent(component: IAppComponent) {
    mComponent = component
  }

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG)
      Timber.plant(DebugTree())
//    else
//      Timber.plant(TimberReleaseTree())
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(AppContextWrapper.wrap(base))
  }
}