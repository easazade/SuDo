package ir.easazade.jesusnote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class DaysAdapter(
  private val days: List<Day>
) : PagerAdapter() {

  override fun isViewFromObject(view: View, objectt: Any): Boolean = view == objectt

  override fun getCount(): Int = days.size

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = LayoutInflater.from(container.context)
    val view = inflater.inflate(R.layout.page_item_day, container, false)
    container.addView(view)
    view.findViewById<TextView>(R.id.texxxxt).text = days[position].dateTime.dayName
    return view
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }
}