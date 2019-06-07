package ir.easazade.jesusnote.sdk

import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.Lifecycle
import io.reactivex.disposables.CompositeDisposable
import ir.easazade.jesusnote.App
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.page.HomePage
import ir.easazade.jesusnote.sdk.Navigation.PageInitialization

infix fun <A : Arguments, P : Page<A>> Any.load(page: P): PageInitialization<A, P> {
  return App.component().navigation().initializePage(page)
}

class Navigation(
  private val container: FrameLayout,
  private val activity: BaseActivity
) {

  private val mBackStack = PageArgumentsStack(mutableListOf())
  private val subscriptions = CompositeDisposable()

  private var isHomePageStarted = false

  private var homePage: HomePage? = null
  var currentPage: Page<*>? = null

  init {
//    subscriptions.add(connectivityWatcher.getConnectivityChanges().subscribe { newStatus ->
//      if (
//        lastConnectionStatus == SystemConnectivityWatcher.Status.DISCONNETED &&
//        newStatus == SystemConnectivityWatcher.Status.CONNETED
//      ) {
//        currentPage?.onInternetReConnected()
//      }
//      lastConnectionStatus = newStatus
//    })
    activity.lifecycle.addObserver(GenericLifecycleObserver { source, event ->
      if (event == Lifecycle.Event.ON_DESTROY) {
        currentPage?.onExitPage()
        currentPage?.onActivityDestroy()
        subscriptions.clear()
      }
    })
  }

  fun startHomePage() {
    isHomePageStarted = true
    if (homePage == null) {
      homePage = HomePage(activity, R.layout.page_home)
      initializePage(homePage!!).with(HomePage.Args())
    } else {
      goBackToHomePage()
    }
  }

  fun goBackToHomePage() {
    homePage?.let {
      val currentPageIndex = container.indexOfChild(currentPage?.rootView)
      val indexOfHomePage = container.indexOfChild(homePage?.rootView)
      if (currentPageIndex != indexOfHomePage) {
        currentPage?.onExitPage()
        container.removeViewAt(currentPageIndex)
        currentPage = it
//        navigationIndicator.setCurrentAsHomePage()
        mBackStack.clear()
        currentPage?.onPageVisible()
      }
    }
  }

  fun goBack() {
    val lastArgs = mBackStack.popLastArgs()
    lastArgs?.let { args ->
      when (args) {
        is HomePage.Args -> goBackToHomePage()
//        is CategoryPage.Args -> load(CategoryPage(activity, R.layout.page_category)).with(args, false)
//        is SearchPage.Args -> load(SearchPage(activity, R.layout.page_search)).with(args, false)
//        is BusinessPage.Args -> load(BusinessPage(activity, R.layout.page_business)).with(args, false)
//        is ContactUsPage.Args -> load(ContactUsPage(activity, R.layout.page_contactus)).with(args, false)
//        is AboutUsPage.Args -> load(AboutUsPage(activity, R.layout.page_aboutus)).with(args, false)
      }
    }
  }

  fun isAtHomePage(): Boolean = (currentPage == null).or(homePage == currentPage)

  infix fun <A : Arguments, P : Page<A>> initializePage(page: P): PageInitialization<A, P> {
    return PageInitialization(page, this)
  }

  class PageInitialization<A : Arguments, P : Page<A>>(
    val page: P,
    private val navigation: Navigation
  ) {

    fun with(arguments: A, addCurrentPageToBackStack: Boolean = true) {
      page.args = arguments
      if (!navigation.isHomePageStarted) throw RuntimeException(
        """
        navigation action before starting home page
        you must first call startHomePage() in Navigation instance
        """
      )
      navigation.currentPage?.let { current ->
        if (current !is HomePage)
          current.onExitPage()
        val currentPageIndex = navigation.container.indexOfChild(current.rootView)
        val indexOfHomePage = navigation.container.indexOfChild(navigation.homePage?.rootView)
        if (currentPageIndex != indexOfHomePage)
          navigation.container.removeViewAt(currentPageIndex)
        if (addCurrentPageToBackStack)
          navigation.mBackStack.saveArguments(current.args)
      }
      navigation.currentPage = page
      page.rootView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
          page.rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
          page.initializeProperties(page.rootView)
          page.args = arguments
          page.onLoadPage(arguments)
          page.onPageVisible()
        }
      })
      navigation.container.addView(
        page.rootView,
        FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
      )
//      if (navigation.isAtHomePage())
//        navigation.navigationIndicator.setCurrentAsHomePage()
//      else
//        navigation.navigationIndicator.setCurrentAsChildPage()
    }
  }
}