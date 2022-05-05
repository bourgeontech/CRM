package com.git.crm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.R



class SuggestedAdapter(
    val data: MutableList<String>,
    val activity: FragmentActivity?,
    val rvPreffered: RecyclerView,
    val fragmentManager: FragmentManager?,
    val make_bottom_dialogvalues: OnBusdetails
) : RecyclerView.Adapter<SuggestedAdapter.BusListDialogViewHolder>() {

    lateinit var onBusdetailsval: OnBusdetails

    public interface OnBusdetails {
        fun onBus(ward: Int,type: String)
    }

    fun setBusInterface(busdetails: OnBusdetails) {
        onBusdetailsval = busdetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusListDialogViewHolder {
        val view: BusListDialogViewHolder =
            BusListDialogViewHolder(
                LayoutInflater.from(activity).inflate(
                    R.layout.suggested_layout,
                    parent,
                    false
                )
            )

        return view
    }



    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: BusListDialogViewHolder, position: Int) {

        if (position == data.size - 1) {
            holder.tvAddMore.visibility = View.VISIBLE
        } else {
            holder.tvAddMore.visibility = View.GONE
        }

        if(data.size==2)
        {
            holder.tvAddMore.visibility = View.GONE
        }

        holder.tvAddMore.setOnClickListener {
            if(data.size==2)
            {

            }else
            {
                data.add("")
                notifyDataSetChanged()
            }
        }
        holder.tvMake.setOnClickListener {
            make_bottom_dialogvalues.onBus(position,"make")
        }
        holder.tvModel.setOnClickListener {
            make_bottom_dialogvalues.onBus(position,"model")
        }

    }


    class BusListDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMakeId = itemView.findViewById(R.id.tvMakeId) as TextView
        var tvModelId = itemView.findViewById(R.id.tvModelId) as TextView
        var tvMake = itemView.findViewById(R.id.tvMake) as TextView
    var tvModel = itemView.findViewById(R.id.tvModel) as TextView
        var tvAddMore = itemView.findViewById(R.id.tvAddMore) as TextView
//    var rlRow = itemView.findViewById(R.id.rlRow) as RelativeLayout
//    var ivRight = itemView.findViewById(R.id.ivRight) as ImageView


    }
}
