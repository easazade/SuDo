package ir.easazade.jesusnote.mvp.model.local

import io.realm.Realm

interface RealmProvider {
    fun get(): Realm
}