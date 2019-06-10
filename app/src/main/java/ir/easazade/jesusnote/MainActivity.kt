package ir.easazade.jesusnote

import android.content.Context
import android.os.Bundle
import ir.easazade.jesusnote.di.AppComponent
import ir.easazade.jesusnote.di.AppDatabaseModule
import ir.easazade.jesusnote.sdk.BaseActivity
import ir.easazade.jesusnote.utils.AppContextWrapper

class MainActivity : BaseActivity() {

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(AppContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (!App.get(this).isAppComponentSet()) {
      App.get(this).setAppComponent(AppComponent(this, AppDatabaseModule(application)))
    }

    injection().navigation().startHomePage()
  }

  override fun onBackPressed() {
    moveTaskToBack(true)
  }
}
