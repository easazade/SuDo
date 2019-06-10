package ir.easazade.jesusnote.di

import ir.easazade.jesusnote.mvp.model.local.AppDatabase
import ir.easazade.jesusnote.mvp.model.local.RealmProvider

interface IDatabaseModule {
  fun database(): AppDatabase
  fun realmProvider(): RealmProvider
}