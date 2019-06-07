package ir.easazade.jesusnote.utils

import java.sql.Timestamp
import java.util.Calendar
import java.util.GregorianCalendar

class DateTime(private val timestamp: Timestamp) {

  val dayOfMonth: Int
  val month: Int
  val year: Int
  val dayName: String

  companion object {
    private val CALENDAR by lazy { GregorianCalendar() }
  }

  init {
    CALENDAR.time = timestamp
    dayOfMonth = CALENDAR.get(Calendar.DAY_OF_MONTH)
    month = CALENDAR.get(Calendar.MONTH)
    year = CALENDAR.get(Calendar.YEAR)
    dayName = CALENDAR.get(Calendar.DAY_OF_WEEK).toDayOfWeekName()
  }
}

private fun Number.toDayOfWeekName(): String {
  return when {
    this == Calendar.SUNDAY -> "یکشنبه"
    this == Calendar.MONDAY -> "دوشنبه"
    this == Calendar.TUESDAY -> "سه شنبه"
    this == Calendar.WEDNESDAY -> "چهارشنبه"
    this == Calendar.THURSDAY -> "پنجشنبه"
    this == Calendar.FRIDAY -> "جمعه"
    this == Calendar.SATURDAY -> "شنبه"
    else -> ""
  }
}
