package ir.easazade.jesusnote.di

import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.mvp.model.local.AppDatabase
import ir.easazade.jesusnote.mvp.model.local.IDatabase
import ir.easazade.jesusnote.sdk.BaseActivity
import ir.easazade.jesusnote.sdk.Navigation

class AppComponent(private val activity: BaseActivity) : IAppComponent {

  private var mDatabase: IDatabase? = null
  private var mNavigation: Navigation? = null

  override fun database(): IDatabase {
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
}