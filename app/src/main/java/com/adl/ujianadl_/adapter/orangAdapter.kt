package com.adl.ujianadl_.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adl.ujianadl_.R
import com.adl.ujianadl_.database.orangDatabase
import com.adl.ujianadl_.fragment.updateFragment
import com.adl.ujianadl_.model.orangModel
import kotlinx.android.synthetic.main.item_orang.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class orangAdapter (val data :ArrayList<orangModel>) : RecyclerView.Adapter<OrangViewHolder>() {
    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrangViewHolder {

        this.parent =parent

        return OrangViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_orang,parent,false))


    }

    override fun onBindViewHolder(holder: OrangViewHolder, position: Int) {
        holder.bindData(this@orangAdapter,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deleteDataAt( position: Int){
        GlobalScope.launch {

            val deleteData = orangDatabase.getInstance(parent.context).daoOrang()
                .deleteModel(data.get(position))

            data.clear()
            data.addAll( ArrayList(orangDatabase.getInstance(parent.context).daoOrang().getAll()))
            val mainExecutor = ContextCompat.getMainExecutor(parent.context)

            // Execute a task in the main thread
            mainExecutor.execute {
                // You code logic goes here.
                notifyDataSetChanged()
            }

        }

    }

}