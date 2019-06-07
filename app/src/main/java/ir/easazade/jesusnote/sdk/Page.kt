package ir.easazade.jesusnote.sdk

import android.view.View
import ir.easazade.jesusnote.R

abstract class Page<ARG : Arguments>(
  activity: BaseActivity,
  rootViewId: Int
) {

  lateinit var args: ARG

  val rootView: View = activity.layoutInflater.inflate(
    rootViewId,
    activity.findViewById(R.id.FIRST_PLACE_HOLDER),
    false
  )

  fun updateArgs(newArgs: ARG) {
    args = newArgs
  }

  fun reloadPage() {
    onLoadPage(args)
  }

  open fun onPageVisible() {}

  open fun onInternetReConnected() {}

  abstract fun initializeProperties(root: View)

  abstract fun onLoadPage(args: ARG)

  abstract fun onExitPage()

  abstract fun onBackPressed()

  open fun onActivityDestroy() {}
}