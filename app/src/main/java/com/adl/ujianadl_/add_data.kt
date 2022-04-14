package com.adl.ujianadl_

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.adl.ujianadl_.database.orangDatabase
import com.adl.ujianadl_.model.orangModel
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.android.synthetic.main.item_orang.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class add_data : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        btnSendData.setOnClickListener({
            val orangData = orangModel(0,txtName.text.toString(),spinnerGender.selectedItem.toString(),
                txtUmur.text.toString(),spinnerStatus.selectedItem.toString())

            GlobalScope.launch {
                orangDatabase.getInstance(this@add_data).daoOrang().insertOrang(orangData)

                val intent = Intent()
                intent.putExtra("data",orangData)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
    }
}