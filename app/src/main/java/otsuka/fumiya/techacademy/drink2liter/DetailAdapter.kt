package otsuka.fumiya.techacademy.drink2liter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class DetailAdapter(context: Context):BaseAdapter() {
    private val mLayoutInflater:LayoutInflater
    var taskList = mutableListOf<DetailData>()

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }



    override fun getItem(position: Int): Any {
        return taskList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return taskList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView?:mLayoutInflater.inflate(android.R.layout.simple_list_item_2,null)

        val textView1 = view.findViewById<TextView>(android.R.id.text1)
        val textView2 = view.findViewById<TextView>(android.R.id.text2)

        textView1.text = taskList[position].drinkTime
        textView2.text = taskList[position].drinkQuantity

        return view
    }

}