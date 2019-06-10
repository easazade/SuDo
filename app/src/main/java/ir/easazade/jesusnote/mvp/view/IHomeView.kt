package ir.easazade.jesusnote.mvp.view

import ir.easazade.jesusnote.mvp.model.Day

interface IHomeView {

  fun initializeHomeList(days: MutableList<Day>)
}