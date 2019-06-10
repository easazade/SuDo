package ir.easazade.jesusnote

import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.Task
import ir.easazade.jesusnote.utils.DateTime
import ir.easazade.jesusnote.utils.currentDateTime
import java.sql.Timestamp

class FakeData {

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
}