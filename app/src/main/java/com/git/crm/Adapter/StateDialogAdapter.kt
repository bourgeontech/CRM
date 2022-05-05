package com.git.crm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Pojo.StateList
import com.git.crm.R


class StateDialogAdapter(
    var activity: FragmentActivity?,
    val fragmentManager: FragmentManager?,
    var doctorlist: MutableList<StateList?>,
    var statelist: ArrayList<String>,
    var stateidlist: ArrayList<String>

) : RecyclerView.Adapter<StateDialogAdapter.BusListDialogViewHolder>() {

    lateinit var onBusdetailsval: OnBusdetails

    public interface OnBusdetails {
        fun onBus(id: String,name: String)
    }

    fun setBusInterface(busdetails: OnBusdetails) {
        onBusdetailsval = busdetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusListDialogViewHolder {
        val view: BusListDialogViewHolder =
            BusListDialogViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.list_row,
                    parent,
                    false
                )
            )

        return view
    }



    override fun getItemCount(): Int {
        return statelist.size
    }

    override fun onBindViewHolder(holder: BusListDialogViewHolder, position: Int) {
try {
    holder.txtName.text = statelist.get(position)
    //  holder.txtName.text = doctorlist.get(position)?.state
//        holder.txtRegisterNo.text = data!!.get(position).registrationNo
//        holder.ivRight.visibility = View.GONE
//
    holder.txtName.setOnClickListener {

        onBusdetailsval.onBus(
            stateidlist.get(position), statelist.get(position)
        )

    }
}catch (e:Exception){

}

    }

    fun filterList(filternames: ArrayList<String>, filterlist2: ArrayList<String>) {
        try {
            statelist = filternames
            stateidlist = filterlist2
            notifyDataSetChanged()
        }catch (e:Exception){

        }
    }


    class BusListDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtName = itemView.findViewById(R.id.txtName) as TextView
//    var txtRegisterNo = itemView.findViewById(R.id.txtRegisterNo) as TextView
//    var rlRow = itemView.findViewById(R.id.rlRow) as RelativeLayout
//    var ivRight = itemView.findViewById(R.id.ivRight) as ImageView


    }
}
