package ir.easazade.jesusnote.mvp.logic

import kotlinx.coroutines.CoroutineScope

interface BasePresenter : CoroutineScope {
  fun clearSubscriptions()
}