package otsuka.fumiya.techacademy.drink2liter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_detail.*
import java.math.BigDecimal

class DetailActivity : AppCompatActivity() {

    private var mDetailData: DetailData = DetailData()
    private lateinit var mRealm: Realm
    private lateinit var mAdapter: DetailAdapter
    lateinit var preference : SharedPreferences


    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(t: Realm) {
            reload()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        preference = getSharedPreferences("Data", Context.MODE_PRIVATE)


        //Realmの設定
        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        //ListViewの設定
        mAdapter = DetailAdapter(this@DetailActivity)

        // ListViewをタップしたときの処理
        listView1.setOnItemClickListener{parent,_,position,_->
            val detail = parent.adapter.getItem(position) as DetailData

            // ダイアログを表示する
            val builder = AlertDialog.Builder(this@DetailActivity)
            builder.setTitle("削除")
            builder.setMessage(detail.drinkTime + "を削除しますか。")

            builder.setPositiveButton("OK"){_,_ ->
                val result = mRealm.where(DetailData::class.java).equalTo("id",detail.id).findAll()

                mRealm.beginTransaction()

                val preStr = preference.getString("sumDrinkQuantity","0.0")
                val editor = preference.edit()
                var tmp = preStr!!.toBigDecimal()
                var tmp2 = detail.drinkQuantity.removeSuffix("ml").toBigDecimal()
                val value =BigDecimal.valueOf(1000)
                tmp2 = tmp2.divide(value)
                //tmp = tmp - tmp2
                val tmpStr = tmp2.toString().substring(0,3)
                editor.putString("sumDrinkQuantity", tmpStr)
                editor.commit()

                result.deleteAllFromRealm()

                mRealm.commitTransaction()


            }

            builder.setNegativeButton("CANCEL", null)

            val dialog = builder.create()
            dialog.show()
        }

        reload()
    }

    private fun reload(){
        //Realmデータベースから、、「全てのデータを取得して新しい時間順に並べた結果」を取得
        val RealmResults = mRealm.where(DetailData::class.java).findAll().sort("drinkTime", Sort.DESCENDING)

        //上記の結果を、TaskListとしてセットする
        mAdapter.taskList = mRealm.copyFromRealm(RealmResults)

        listView1.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }

}
