package ir.easazade.jesusnote.page

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.ViewPager
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.injection
import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.Task
import ir.easazade.jesusnote.mvp.view.IHomeView
import ir.easazade.jesusnote.page.HomePage.Args
import ir.easazade.jesusnote.sdk.Arguments
import ir.easazade.jesusnote.sdk.BaseActivity
import ir.easazade.jesusnote.sdk.Page
import ir.easazade.jesusnote.utils.DateTime
import ir.easazade.jesusnote.utils.currentDateTime
import ir.easazade.jesusnote.view.adapters.DaysAdapter
import ir.easazade.jesusnote.view.adapters.ViewPagerCardTransformer
import ir.easazade.jesusnote.view.dialog.AddTaskDialog
import java.sql.Timestamp

class HomePage(
  private val activity: BaseActivity,
  @LayoutRes private val layoutId: Int
) : Page<Args>(activity, layoutId), IHomeView {

  private val addTaskDialog by lazy {
    AddTaskDialog.newInstance().apply {
      setOnSaveButtonClieckListener { task ->
        injection().database().saveTask(task)
      }
    }
  }

  private lateinit var mAddTask: TextView
  private lateinit var mDaysViewPager: ViewPager

  override fun initializeProperties(root: View) {
    mAddTask = root.findViewById(R.id.mHomeAddTask)
    mDaysViewPager = root.findViewById(R.id.mHomeViewPager)
  }

  override fun onLoadPage(args: Args) {

    val tasks = mutableListOf(
      Task(0, currentDateTime(), "یک روز خوب و بارونی که می شه خیلی خوب و عالی زندگی کرد", false),
      Task(0, currentDateTime(), "رضا اشبلان", true),
      Task(0, currentDateTime(), "خرید خونه", true),
      Task(0, currentDateTime(), "خرید لباس", false),
      Task(0, currentDateTime(), "برم پیش مهدی", false),
      Task(0, currentDateTime(), "برم عروسی", false),
      Task(0, currentDateTime(), "خواب", false),
      Task(0, currentDateTime(), "ورزش", false),
      Task(0, currentDateTime(), "برم شرکت", false)
    )

    val days = listOf(
      Day(DateTime(Timestamp.valueOf("2019-06-25 20:50:00")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-24 20:50:00")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-23 20:50:30")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-22 20:50:42")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-21 20:50:00")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-20 20:50:00")), tasks)
    )

    val adapter = DaysAdapter(days)
    mDaysViewPager.adapter = adapter
    mDaysViewPager.offscreenPageLimit = 3
    mDaysViewPager.setPageTransformer(true, ViewPagerCardTransformer())

    mAddTask.setOnClickListener {
      addTaskDialog.show(activity.supportFragmentManager, AddTaskDialog.TAG)
    }
  }

  override fun onExitPage() {
  }

  override fun onBackPressed() {
    activity.moveTaskToBack(true)
  }

  class Args : Arguments()
}