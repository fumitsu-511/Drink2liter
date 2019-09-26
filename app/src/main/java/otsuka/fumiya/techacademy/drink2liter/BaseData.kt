package otsuka.fumiya.techacademy.drink2liter

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class BaseData : RealmObject(), Serializable {

    var wakeUpTime : String = ""
    var offTime : String = ""
    var pushTiming: Int = 0
    var goalDrinkQuantity : Double  = 0.0

    @PrimaryKey
    var id : Int = 0


}