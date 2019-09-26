package otsuka.fumiya.techacademy.drink2liter

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_time.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity() {

    private var mBaseData: BaseData = BaseData()

    private lateinit var mRealm: Realm

    lateinit var preference : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        preference = getSharedPreferences("Data", Context.MODE_PRIVATE)

        decideButton.setOnClickListener { _->

//            //Realmの設定
//            mRealm = Realm.getDefaultInstance()
//
//            mRealm.beginTransaction()
//
//            //val RealmRes = mRealm.where(BaseData::class.java).findAll()
//
//            var tmp = drinkGoalSpiner.selectedItem.toString().removeSuffix("ℓ")
//            var tmpD= tmp.toDouble()
//            mBaseData.goalDrinkQuantity = tmpD
//
//            mBaseData.wakeUpTime = time1Spiner.selectedItem.toString()
//
//            mBaseData.offTime = time2Spiner.selectedItem.toString()
//
//            tmp =time3Spinner.selectedItem.toString().removeSuffix("時間ごと")
//            var tmpI = tmp.toInt()
//            mBaseData.pushTiming = tmpI
//            mBaseData.id = 0
//
//            Log.d("test_goalDrinkQuantity",tmpD.toString())
//            Log.d("test_wakeUpTime",time1Spiner.selectedItem.toString())
//            Log.d("test_offTime",time2Spiner.selectedItem.toString())
//            Log.d("test_pushTiming",tmpI.toString())
//
//
//            //閉じる
//            mRealm.copyToRealmOrUpdate(mBaseData!!)
//            mRealm.commitTransaction()

            val editor = preference.edit()
            val tmp = drinkGoalSpiner.selectedItem.toString().removeSuffix("ℓ")
            editor.putString("goalDrinkQuantity", tmp)
            editor.commit()


        }


    }

//    override fun onDestroy(){
//        super.onDestroy()
//
//        mRealm.close()
//    }
}
