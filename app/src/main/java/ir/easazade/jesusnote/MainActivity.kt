package ir.easazade.jesusnote

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.easazade.jesusnote.utils.AppContextWrapper
import ir.easazade.jesusnote.utils.DateTime
import ir.easazade.jesusnote.view.ViewPagerCardTransformer
import kotlinx.android.synthetic.main.activity_main.mDaysViewPager
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(AppContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val days = listOf(
      Day(DateTime(Timestamp.valueOf("2019-06-25 20:50:00"))),
      Day(DateTime(Timestamp.valueOf("2019-06-24 20:50:00"))),
      Day(DateTime(Timestamp.valueOf("2019-06-23 20:50:00"))),
      Day(DateTime(Timestamp.valueOf("2019-06-22 20:50:00"))),
      Day(DateTime(Timestamp.valueOf("2019-06-21 20:50:00"))),
      Day(DateTime(Timestamp.valueOf("2019-06-20 20:50:00")))
    )

    val adapter = DaysAdapter(days)
    mDaysViewPager.adapter = adapter
//    mDaysViewPager.setPageTransformer(false, ViewPagerCardTransformer())
  }
}
