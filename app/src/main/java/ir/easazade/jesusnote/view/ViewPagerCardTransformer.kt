package ir.easazade.jesusnote.view

import android.view.View
import androidx.viewpager.widget.ViewPager

class ViewPagerCardTransformer : ViewPager.PageTransformer {
  override fun transformPage(page: View, position: Float) {
    if (position >= 0) {
      page.scaleX = 0.7f - 0.05f * position
      page.scaleY = 0.7f
      page.translationX = -page.width * position
      page.translationY = 30 * position
    }
  }
}