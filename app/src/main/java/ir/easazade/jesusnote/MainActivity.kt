package ir.easazade.jesusnote

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.easazade.jesusnote.model.Day
import ir.easazade.jesusnote.model.Task
import ir.easazade.jesusnote.utils.AppContextWrapper
import ir.easazade.jesusnote.utils.DateTime
import ir.easazade.jesusnote.utils.currentDateTime
import ir.easazade.jesusnote.view.adapters.DaysAdapter
import ir.easazade.jesusnote.view.adapters.ViewPagerCardTransformer
import ir.easazade.jesusnote.view.dialog.AddTaskDialog
import kotlinx.android.synthetic.main.activity_main.mAddTask
import kotlinx.android.synthetic.main.activity_main.mDaysViewPager
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {

  private val addTaskDialog by lazy {
    AddTaskDialog.newInstance()
  }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(AppContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

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
      Day(DateTime(Timestamp.valueOf("2019-06-23 20:50:00")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-22 20:50:00")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-21 20:50:00")), tasks),
      Day(DateTime(Timestamp.valueOf("2019-06-20 20:50:00")), tasks)
    )

    val adapter = DaysAdapter(days)
    mDaysViewPager.adapter = adapter
    mDaysViewPager.offscreenPageLimit = 3
    mDaysViewPager.setPageTransformer(true, ViewPagerCardTransformer())

    mAddTask.setOnClickListener {
      addTaskDialog.show(supportFragmentManager, AddTaskDialog.TAG)
    }
  }

  override fun onBackPressed() {
    moveTaskToBack(true)
  }
}
