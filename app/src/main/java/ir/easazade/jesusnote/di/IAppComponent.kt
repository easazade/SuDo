package ir.easazade.jesusnote.di

import ir.easazade.jesusnote.mvp.logic.HomePresenter
import ir.easazade.jesusnote.mvp.model.local.IAppDatabase
import ir.easazade.jesusnote.mvp.view.IHomeView
import ir.easazade.jesusnote.sdk.IAppThreads
import ir.easazade.jesusnote.sdk.Navigation

interface IAppComponent {

  fun database(): IAppDatabase
  fun navigation(): Navigation
  fun threads(): IAppThreads
  fun newHomePresenter(homeView: IHomeView): HomePresenter
}