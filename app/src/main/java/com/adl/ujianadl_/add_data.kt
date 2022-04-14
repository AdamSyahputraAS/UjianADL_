package com.adl.ujianadl_

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.adl.ujianadl_.database.orangDatabase
import com.adl.ujianadl_.model.orangModel
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.android.synthetic.main.item_orang.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.jar.Manifest

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
                setResult(RESULT_OK,intent)
                finish()
            }

        })

        buttonGPS.isEnabled = false

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                111
            )
        else
            buttonGPS.isEnabled = true

        buttonGPS.setOnClickListener {
            var city: String = txtLocation.text.toString()
            var gc = Geocoder(this, Locale.getDefault())
            var addresses = gc.getFromLocationName(city, 2)
            var address: Address = addresses.get(0)
            hasil_longtitude.visibility =  View.VISIBLE
            hasil_longtitude.setText("${address.latitude} \n ${address.longitude} \n ${address.locality}")
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            buttonGPS.isEnabled = true
    }
    }
