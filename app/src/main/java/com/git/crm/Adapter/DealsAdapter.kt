package com.git.crm.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Pojo.DealList
import com.git.crm.Pojo.RemarkList
import com.git.greenexvendor.Pagination.Constant
import kotlinx.android.synthetic.main.row_lead.view.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


import kotlinx.android.synthetic.main.fragment_customer_details_two.*


class DealsAdapter(
    val itemsCells: List<DealList>?,
    val activity: FragmentActivity,
    val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    public interface orderSelectionListener{
        fun onOrderselection(leadid:String,heading:String,remark:String,date:String,type:String)
    }

    lateinit var mcontext: Context

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

//    fun addData(dataViews: MutableList<GetLeadsPojo?>) {
//        this.itemsCells?.addAll(dataViews!!)
//        notifyDataSetChanged()
//    }
//
//    fun getItemAtPosition(position: Int): GetLeadsPojo? {
//        return itemsCells?.get(position)
//    }
//
//    fun addLoadingView() {
//        //Add loading item
//        Handler().post {
//            itemsCells.add(null)
//            (itemsCells?.size)?.minus(1)?.let { notifyItemInserted(it) }
//        }
//    }
//
//    fun removeLoadingView() {
//        //Remove loading item
//        if (itemsCells.size != 0) {
//            itemsCells.removeAt(itemsCells?.size - 1)
//            notifyItemRemoved(itemsCells?.size)
//        }
//    }
//
//
//    fun removeView(pos: Int) {
//        //Remove loading item
//        if (itemsCells.size != 0) {
//            itemsCells.removeAt(pos)
//            notifyDataSetChanged()
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(com.git.crm.R.layout.row_status, parent, false)
            ItemViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(mcontext).inflate(com.git.crm.R.layout.progress_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return itemsCells?.size!!
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsCells?.get(position) == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM) {
           // holder.itemView.tvDate.text = itemsCells?.get(position)?.createdDate
            holder.itemView.tvName.text = itemsCells?.get(position)?.deal
            holder.itemView.tvDate.text =itemsCells?.get(position)?.createdDate

//            holder.itemView.tvEdit.setOnClickListener {
//                getBottomSheet(itemsCells.get(position)?.salesId)
//            }
//            holder.itemView.llContainer.setOnClickListener {
//                itemsCells?.get(position)?.orderId?.let { it1 ->
//                    orderselectionObj.onOrderselection(
//                        it1
//                    )
//                }
//            }
        }
    }

//    private fun getBottomSheet(salesId: Int?) {
//        val c = Calendar.getInstance().time
//        println("Current time => $c")
//
//        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
//        val formattedDate = df.format(c)
//
//        val bottomSheetDialog = BottomSheetDialog(activity)
//        bottomSheetDialog.setContentView(com.git.crm.R.layout.follow_up_bottomsheet)
//
//        val edtHeading = bottomSheetDialog.findViewById<EditText>(com.git.crm.R.id.edtHeading)
//        val edtRemark = bottomSheetDialog.findViewById<EditText>(com.git.crm.R.id.edtRemark)
//        val tvDelete = bottomSheetDialog.findViewById<TextView>(com.git.crm.R.id.tvDelete)
//        val tvSave = bottomSheetDialog.findViewById<TextView>(com.git.crm.R.id.tvSave)
//        val tvDate = bottomSheetDialog.findViewById<TextView>(com.git.crm.R.id.tvDate)
//
//        tvDate?.text=formattedDate
//        bottomSheetDialog.show()
//        tvSave?.setOnClickListener {
//            lead_interface.onOrderselection(salesId.toString(),edtHeading?.text.toString(),edtRemark?.text.toString(),"","save")
//            bottomSheetDialog.dismiss()
//        }
//    }

    fun parseDateToddMMyyyy(time: String?): String? {
       // val inputPattern = "yyyy-MM-dd hh:mm:ss"
        val inputPattern = "MMM dd yyyy hh:mm aaa"
        val outputPattern = "dd-MMM-yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

}