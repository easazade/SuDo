package ir.easazade.jesusnote.di

import ir.easazade.jesusnote.mvp.model.local.IDatabase
import ir.easazade.jesusnote.sdk.Navigation

interface IAppComponent {

  fun database(): IDatabase
  fun navigation():Navigation
}