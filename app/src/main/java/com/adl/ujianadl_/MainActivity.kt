package com.adl.ujianadl_

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.adl.ujianadl_.adapter.orangAdapter
import com.adl.ujianadl_.database.orangDatabase
import com.adl.ujianadl_.model.orangModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db:orangDatabase
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

            GlobalScope.launch {

                lstOrang.clear()
                lstOrang.addAll(ArrayList(getAllData()))

                this@MainActivity.runOnUiThread({
                    orangadapter.notifyDataSetChanged()
                })
            }
        }
    }

    lateinit var orangadapter: orangAdapter
    var lstOrang = ArrayList<orangModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            orangDatabase::class.java, "orangDatabase"
        ).build()

        GlobalScope.launch {

            lstOrang = ArrayList(getAllData())
            this@MainActivity.runOnUiThread({
                orangadapter = orangAdapter(lstOrang)
                listOrang.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = orangadapter
                }
            })

        }
        btnAdd.setOnClickListener({
            val intent = Intent(this@MainActivity,add_data::class.java)
            resultLauncher.launch(intent)
        })
    }

    fun getAllData():List<orangModel>{
        return orangDatabase.getInstance(this@MainActivity).daoOrang().getAll()
    }



}