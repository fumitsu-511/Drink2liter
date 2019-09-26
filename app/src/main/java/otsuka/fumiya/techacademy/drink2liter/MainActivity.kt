package otsuka.fumiya.techacademy.drink2liter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mDetailData: DetailData = DetailData()

    private lateinit var mRealm: Realm

    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(t: Realm) {
            reloadDrinkQuantity()
        }
    }

    lateinit var preference : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        detailButton.setOnClickListener { _ ->
            val intent = Intent(applicationContext, DetailActivity::class.java)
            startActivity(intent)
        }

        optionButton.setOnClickListener { _ ->
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }

        preference = getSharedPreferences("Data", Context.MODE_PRIVATE)

        //Realmの設定
        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        //現在時刻から、目標水分量の設定
        val now = SimpleDateFormat("HH:mm;ss")
        val nowTime = Date()
        val a =now.format(nowTime)




        cupButton.setOnClickListener { _ ->
            addDrinkQuantity()
        }

        bottleButton.setOnClickListener { _->
            addDrinkQuantity2()
        }

        reloadDrinkQuantity()

    }

    private fun reloadDrinkQuantity(){

        drinkQuan.setText(preference.getString("sumDrinkQuantity","0.0"))
        GoalDrinkQuan.setText(preference.getString("goalDrinkQuantity","0.0"))

//        val realm = Realm.getDefaultInstance()
//        val RealmRes = realm.where(DetailData::class.java).findAll()
//
//        val identifier: Int =
//            if (RealmRes.max("id") != null){
//                RealmRes.max("id")!!.toInt()
//            } else{
//                0
//            }
//
//        val RealmResLastId = realm.where(DetailData::class.java).equalTo("id",identifier).findFirst()
//        drinkQuan.text =
//            if (RealmResLastId != null){
//                RealmResLastId!!.sumDrinkQuantity.toString().substring(0,3)
//            }else{
//                "0.0"
//            }

    }

    private fun addDrinkQuantity(){

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

            val RealmRes = realm.where(DetailData::class.java).findAll()

            val identifier: Int =
                if (RealmRes.max("id") != null){
                    RealmRes.max("id")!!.toInt()+1
                } else{
                    0
                }

            mDetailData!!.id = identifier

        val cupQuntity = cupSpinner.selectedItem.toString()
        mDetailData!!.drinkQuantity = cupQuntity
        var tempStr = cupQuntity.removeSuffix("ml")
        var tempDub : Double
        tempDub = tempStr.toDouble()
        tempDub = tempDub / 1000


        val preStr = preference.getString("sumDrinkQuantity","0.0")
         if (!preStr.equals("0.0") ) {

            val editor = preference.edit()
            val tmp = preStr!!.toDouble() + tempDub
             val tmpStr = String.format("%.2f",tmp)
            //val tmpStr = tmp.toString().substring(0,3)
             editor.putString("sumDrinkQuantity", tmpStr)
            editor.commit()

        }else{
            val editor = preference.edit()
            val tmp = tempDub
            //val tmpStr = tmp.toString().substring(0,3)
             val tmpStr = String.format("%.2f",tmp)
            editor.putString("sumDrinkQuantity", tmpStr)
            editor.commit()

        }

//        val RealmResLastId = realm.where(DetailData::class.java).equalTo("id",identifier-1).findFirst()
//        if (RealmResLastId != null) {
//
//            val editor = preference.edit()
//            val tmp = RealmResLastId!!.sumDrinkQuantity + tempDub
//            val tmpStr = tmp.toString().substring(0,3)
//            editor.putString("sumDrinkQuantity", tmpStr)
//            editor.commit()
//
//        }else{
//            val editor = preference.edit()
//            val tmp = tempDub
//            val tmpStr = tmp.toString().substring(0,3)
//            editor.putString("sumDrinkQuantity", tmpStr)
//            editor.commit()
//
//        }

        //時刻の取得
        val df = SimpleDateFormat("HH:mm;ss")
        val time = Date()
        mDetailData!!.drinkTime =df.format(time).toString()

        //閉じる
        realm.copyToRealmOrUpdate(mDetailData!!)
        realm.commitTransaction()

    }


    private fun addDrinkQuantity2(){

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        val RealmRes = realm.where(DetailData::class.java).findAll()

        val identifier: Int =
            if (RealmRes.max("id") != null){
                RealmRes.max("id")!!.toInt()+1
            } else{
                0
            }

        mDetailData!!.id = identifier

        val botlleQuntity = bottleSpinner.selectedItem.toString()
        mDetailData!!.drinkQuantity = botlleQuntity
        var tempStr = botlleQuntity.removeSuffix("ml")
        var tempDub : Double
        tempDub = tempStr.toDouble()
        tempDub = tempDub / 1000


        val preStr = preference.getString("sumDrinkQuantity","0.0")
        if (!preStr.equals("0.0") ) {

            val editor = preference.edit()
            val tmp = preStr!!.toDouble() + tempDub
            val tmpStr = tmp.toString().substring(0,3)
            editor.putString("sumDrinkQuantity", tmpStr)
            editor.commit()

        }else{
            val editor = preference.edit()
            val tmp = tempDub
            val tmpStr = tmp.toString().substring(0,3)
            editor.putString("sumDrinkQuantity", tmpStr)
            editor.commit()

        }

//        val RealmResLastId = realm.where(DetailData::class.java).equalTo("id",identifier-1).findFirst()
//        if (RealmResLastId != null) {
//
//            val editor = preference.edit()
//            val tmp = RealmResLastId!!.sumDrinkQuantity + tempDub
//            val tmpStr = tmp.toString().substring(0,3)
//            editor.putString("sumDrinkQuantity", tmpStr)
//            editor.commit()
//
//        }else{
//            val editor = preference.edit()
//            val tmp = tempDub
//            val tmpStr = tmp.toString().substring(0,3)
//            editor.putString("sumDrinkQuantity", tmpStr)
//            editor.commit()
//
//        }

        //時刻の取得
        val df = SimpleDateFormat("HH:mm;ss")
        val time = Date()
        mDetailData!!.drinkTime =df.format(time).toString()

        //閉じる
        realm.copyToRealmOrUpdate(mDetailData!!)
        realm.commitTransaction()

    }


    override fun onDestroy(){
        super.onDestroy()

        mRealm.close()
    }


}
