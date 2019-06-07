package ir.easazade.jesusnote.view.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.R.layout
import ir.easazade.jesusnote.mvp.model.Day

class DaysAdapter(
  private val days: List<Day>
) : PagerAdapter() {

  override fun isViewFromObject(view: View, objectt: Any): Boolean = view == objectt as ConstraintLayout

  override fun getCount(): Int = days.size

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = LayoutInflater.from(container.context)
    val view = inflater.inflate(layout.page_item_day, container, false) as ConstraintLayout
    container.addView(view)
    val list = view.findViewById<RecyclerView>(R.id.pageItemDay_list)
    val title = view.findViewById<TextView>(R.id.pageItemDay_title)
    list.layoutManager = LinearLayoutManager(container.context)
    list.adapter = TasksRecyclerAdapter(days[position].tasks.toMutableList())
    title.text = days[position].dateTime.getDateAsString()
    if (Build.VERSION.SDK_INT >= 21)
      title.elevation = 5f
    return view
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }
}