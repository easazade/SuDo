package ir.easazade.jesusnote.sdk

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import ir.easazade.jesusnote.utils.AppContextWrapper
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

  fun hideSoftKeyboard() {
    try {
      val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
      val focus = currentFocus?.windowToken
      inputMethodManager.hideSoftInputFromWindow(focus, 0)
    } catch (e: Exception) {
      Timber.e(e, "exception when hiding soft keyboard")
    }
  }

//  public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//    super.onActivityResult(requestCode, resultCode, data)
//
//    if (requestCode == GoogleAuthUtils.REQUEST_CODE) {
//      // The Task returned from this call is always completed, no need to attach
//      // a listener.
//      val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//      App.component().googleAuthUtils().reportSignInResult(this, task)
//    }
//  }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(AppContextWrapper.wrap(newBase))
  }
}