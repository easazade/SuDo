package ir.easazade.jesusnote.sdk

class PageArgumentsStack(private val mStack: MutableList<Arguments>) {

  fun saveArguments(args: Arguments) {
    mStack.add(args)
  }

  /***
   * returns the last state of the given Frag is Any
   */
//  fun <T : ViewState> popLastStateOf(viewStateClass: Class<T>, clearTop: Boolean): T? {
//    var index = -1
//    val state = mStack.lastOrNull { viewState ->
//      viewState::class.java.isAssignableFrom(viewStateClass)
//    }
//    mStack.indexOf(state)
//    if ((index != -1).and(clearTop))//clear top and popping the matching state
//      for (i in index until mStack.size) {
//        mStack.removeAt(i)
//      }
//    return if (state != null)
//      (state as T)
//    else
//      null
//  }

  /***
   * used for back actions in navigator
   */
  fun popLastArgs(): Arguments? =
    if (mStack.isNotEmpty())
      mStack.removeAt(mStack.lastIndex)
    else null

  fun clear() {
    mStack.clear()
  }

  fun peakLastArguments(): Arguments? = mStack.lastOrNull()
}