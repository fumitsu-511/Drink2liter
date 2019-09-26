package otsuka.fumiya.techacademy.drink2liter

import android.app.Application
import io.realm.Realm

class Realm:Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

}