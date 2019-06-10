package ir.easazade.jesusnote.di

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import ir.easazade.jesusnote.mvp.model.local.AppDatabase
import ir.easazade.jesusnote.mvp.model.local.RealmProvider

class AppDatabaseModule(app: Application) : IDatabaseModule {

  private var database: AppDatabase? = null

  init {
    Realm.init(app)
    val realmConfig = RealmConfiguration.Builder()
      .name("sudo_app_database")
      .schemaVersion(1)
      .deleteRealmIfMigrationNeeded().build()
    Realm.setDefaultConfiguration(realmConfig)
  }

  override fun realmProvider(): RealmProvider = object : RealmProvider {
    override fun get(): Realm = Realm.getDefaultInstance()
  }

  override fun database(): AppDatabase =
    if (database != null)
      database!!
    else {
      database = AppDatabase(realmProvider())
      database!!
    }
}