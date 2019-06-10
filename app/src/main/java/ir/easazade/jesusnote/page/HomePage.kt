package ir.easazade.jesusnote.page

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.ViewPager
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.injection
import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.view.IHomeView
import ir.easazade.jesusnote.page.HomePage.Args
import ir.easazade.jesusnote.sdk.Arguments
import ir.easazade.jesusnote.sdk.BaseActivity
import ir.easazade.jesusnote.sdk.Page
import ir.easazade.jesusnote.view.adapters.DaysAdapter
import ir.easazade.jesusnote.view.adapters.ViewPagerCardTransformer
import ir.easazade.jesusnote.view.dialog.AddTaskDialog

class HomePage(
  private val activity: BaseActivity,
  @LayoutRes private val layoutId: Int
) : Page<Args>(activity, layoutId), IHomeView {

  private val presenter = injection().newHomePresenter(this)
  private lateinit var mAddTask: TextView
  private lateinit var mDaysViewPager: ViewPager
  private var mDaysAdapter: DaysAdapter? = null

  private val addTaskDialog by lazy {
    AddTaskDialog.newInstance().apply {
      setOnSaveButtonClieckListener { task ->
        presenter.saveTask(task)
      }
    }
  }

  override fun initializeProperties(root: View) {
    mAddTask = root.findViewById(R.id.mHomeAddTask)
    mDaysViewPager = root.findViewById(R.id.mHomeViewPager)
    mAddTask.setOnClickListener {
      addTaskDialog.show(activity.supportFragmentManager, AddTaskDialog.TAG)
    }
  }

  override fun onLoadPage(args: Args) {
  }

  override fun onExitPage() {
  }

  override fun onBackPressed() {
    activity.moveTaskToBack(true)
  }

  class Args : Arguments()

  //######################################## IHomeView ###############################################

  override fun initializeHomeList(days: MutableList<Day>) {
    mDaysAdapter = DaysAdapter(days)
    mDaysViewPager.adapter = mDaysAdapter
    mDaysViewPager.offscreenPageLimit = 3
    mDaysViewPager.setPageTransformer(true, ViewPagerCardTransformer())
  }
}