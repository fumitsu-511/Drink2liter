package otsuka.fumiya.techacademy.drink2liter

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class DetailData: RealmObject(), Serializable {

    var drinkTime : String = ""  //飲水時間
    var drinkQuantity : String = ""  //飲水量
    var sumDrinkQuantity : Double  = 0.0  //合計飲水量

    //idをプライマリーキーとして設定
    @PrimaryKey
    var id: Int = 0


}