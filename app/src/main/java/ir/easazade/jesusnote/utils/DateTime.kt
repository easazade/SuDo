package ir.easazade.jesusnote.utils

import java.sql.Timestamp
import java.util.Calendar
import java.util.GregorianCalendar

class DateTime(val timestamp: Timestamp) {

  val hour: Int
  val minute: Int
  val second: Int
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
    month = CALENDAR.get(Calendar.MONTH) + 1
    year = CALENDAR.get(Calendar.YEAR)
    hour = CALENDAR.get(Calendar.HOUR_OF_DAY)
    minute = CALENDAR.get(Calendar.MINUTE)
    second = CALENDAR.get(Calendar.SECOND)
    dayName = CALENDAR.get(Calendar.DAY_OF_WEEK).toDayOfWeekName()
  }

  fun getTimeAsString() = "${addZero(hour)}:${addZero(minute)}"
  fun getDateAsString() = "${addZero(year)}-${addZero(month)}-${addZero(dayOfMonth)}"

  private fun addZero(value: Int): String =
    if (value < 10 && value > -1) "0$value" else value.toString()

  override fun toString(): String =
    "$year-$month-$dayOfMonth $hour:$minute:$second"
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
