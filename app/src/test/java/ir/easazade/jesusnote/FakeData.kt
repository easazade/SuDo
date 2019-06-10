package ir.easazade.jesusnote

import ir.easazade.jesusnote.mvp.model.Day
import ir.easazade.jesusnote.mvp.model.Task
import ir.easazade.jesusnote.utils.DateTime
import ir.easazade.jesusnote.utils.currentDateTime
import java.sql.Timestamp

class FakeData {

  val tasks = mutableListOf(
    Task(0, currentDateTime(), "یک روز خوب و بارونی که می شه خیلی خوب و عالی زندگی کرد", false),
    Task(1, currentDateTime(), "رضا اشبلان", true),
    Task(2, currentDateTime(), "خرید خونه", true),
    Task(3, currentDateTime(), "خرید لباس", false),
    Task(4, currentDateTime(), "برم پیش مهدی", false),
    Task(5, currentDateTime(), "برم عروسی", false),
    Task(6, currentDateTime(), "خواب", false),
    Task(7, currentDateTime(), "ورزش", false),
    Task(8, currentDateTime(), "برم شرکت", false)
  )

  val days = listOf(
    Day(0,DateTime(Timestamp.valueOf("2019-06-25 20:50:00")), tasks),
    Day(1,DateTime(Timestamp.valueOf("2019-06-24 20:50:00")), tasks),
    Day(2,DateTime(Timestamp.valueOf("2019-06-23 20:50:30")), tasks),
    Day(3,DateTime(Timestamp.valueOf("2019-06-22 20:50:42")), tasks),
    Day(4,DateTime(Timestamp.valueOf("2019-06-21 20:50:00")), tasks),
    Day(5,DateTime(Timestamp.valueOf("2019-06-20 20:50:00")), tasks)
  )
}