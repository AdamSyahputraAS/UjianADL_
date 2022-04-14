package com.adl.ujianadl_.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_orang.view.*

class OrangViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val nama = view.textViewNama
    val gender = view.textViewGender
    val umur = view.textViewUmur
    val status = view.textViewStatus
    val delete = view.buttonHapus


    fun bindData(adapter:orangAdapter,position: Int) {


        nama.setText(adapter.data.get(position).nama)
        gender.setText(adapter.data.get(position).gender)
        umur.setText(adapter.data.get(position).umur)
        status.setText(adapter.data.get(position).status)

        delete.setOnClickListener {
            adapter.deleteDataAt(position)

            }

    }
}