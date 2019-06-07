package ir.easazade.jesusnote.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import ir.easazade.jesusnote.R.id
import ir.easazade.jesusnote.R.layout
import ir.easazade.jesusnote.model.Day

class DaysAdapter(
  private val days: List<Day>
) : PagerAdapter() {

  override fun isViewFromObject(view: View, objectt: Any): Boolean = view == objectt as ConstraintLayout

  override fun getCount(): Int = days.size

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = LayoutInflater.from(container.context)
    val view = inflater.inflate(layout.page_item_day, container, false)
    container.addView(view)
    view.findViewById<TextView>(id.texxxxt).text = days[position].dateTime.dayName
    return view
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }
}