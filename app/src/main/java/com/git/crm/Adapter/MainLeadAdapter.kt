package com.git.crm.Adapter

import android.app.DatePickerDialog
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Pojo.GetLeadsPojo
import com.git.greenexvendor.Pagination.Constant
import kotlinx.android.synthetic.main.row_lead.view.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.widget.EditText


import android.widget.TextView
import com.git.crm.R

import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.follow_up_bottomsheet.view.*

import kotlinx.android.synthetic.main.row_lead.view.tvDate


class MainLeadAdapter(
    val itemsCells: MutableList<GetLeadsPojo?>,
    val activity: FragmentActivity,
    val fragmentManager: FragmentManager,
    val lead_interface: orderSelectionListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    public interface orderSelectionListener{
        fun onOrderselection(leadid:String,heading:String,remark:String,date:String,type:String)
    }

    lateinit var mcontext: Context
    private var date: String = ""
    var cal = Calendar.getInstance()
    private var date2: String = ""
    var cal2 = Calendar.getInstance()
    private var sendFormat: String = ""
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addData(dataViews: MutableList<GetLeadsPojo?>) {
        this.itemsCells?.addAll(dataViews!!)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): GetLeadsPojo? {
        return itemsCells?.get(position)
    }

    fun addLoadingView() {
        //Add loading item
        Handler().post {
            itemsCells.add(null)
            (itemsCells?.size)?.minus(1)?.let { notifyItemInserted(it) }
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (itemsCells.size != 0) {
            itemsCells.removeAt(itemsCells?.size - 1)
            notifyItemRemoved(itemsCells?.size)
        }
    }


    fun removeView(pos: Int) {
        //Remove loading item
        if (itemsCells.size != 0) {
            itemsCells.removeAt(pos)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == Constant.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_lead_three, parent, false)
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
        return if (itemsCells.get(position) == null) {
            Constant.VIEW_TYPE_LOADING
        } else {
            Constant.VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM) {
           // holder.itemView.tvDate.text = itemsCells?.get(position)?.createdDate
            holder.itemView.tvName.text = itemsCells?.get(position)?.customerName+" "+itemsCells?.get(position)?.Customer_LastName
            holder.itemView.tvDate.text =  parseDateToddMMyyyy(itemsCells?.get(position)?.createdDate)

            holder.itemView.tvMobile.text="Phone : "+itemsCells?.get(position)?.customerMobNo
            holder.itemView.cvAll.setOnClickListener {
              //getBottomSheet(itemsCells.get(position)?.salesId)
                lead_interface.onOrderselection(itemsCells?.get(position)?.salesId.toString(),itemsCells?.get(position)?.customerName!!,itemsCells?.get(position)?.customerMobNo!!,itemsCells?.get(position)?.createdDate!!,"")
            }
            if(itemsCells?.get(position)?.Lead_status.equals("1"))
            {
                holder.itemView.ivNew.visibility=View.VISIBLE
            }else
            {  holder.itemView.ivNew.visibility=View.GONE

            }

            holder.itemView.tvadded.visibility=View.VISIBLE
            holder.itemView.tvadded.text="Added by "+itemsCells?.get(position)?.addedby
//            if(itemsCells?.get(position)?.leadLoginID!!.equals(userId))
//            holder.itemView.tvadded.setText("Added By Me")
//            else
//                holder.itemView.tvadded.setText("Added By Others")
//            holder.itemView.llContainer.setOnClickListener {
//                itemsCells?.get(position)?.orderId?.let { it1 ->
//                    orderselectionObj.onOrderselection(
//                        it1
//                    )
//                }
//            }
        }
    }

    private fun getBottomSheet(salesId: Int?) {
        val c = Calendar.getInstance().time
        println("Current time => $c")
        val calendar: Calendar = Calendar.getInstance()
        val mdformat2 = SimpleDateFormat("dd MMM yyyy")
        val mdformat3 = SimpleDateFormat("dd MM yyyy")
        val strDate1 = mdformat2.format(calendar.getTime())
        val strDate2 = mdformat3.format(calendar.getTime())

        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)

//        val bottomSheetDialog = BottomSheetDialog(activity)
//        bottomSheetDialog.setContentView(R.layout.follow_up_bottomsheet)
        val dialog = BottomSheetDialog(activity)
        val bottomSheet = activity.layoutInflater.inflate(R.layout.follow_up_bottomsheet, null)
//        val edtHeading = bottomSheetDialog.findViewById<EditText>(R.id.edtHeading)
//        val edtRemark = bottomSheetDialog.findViewById<EditText>(R.id.edtRemark)
//        val tvDelete = bottomSheetDialog.findViewById<TextView>(R.id.tvDelete)
//        val tvSave = bottomSheetDialog.findViewById<TextView>(R.id.tvSave)
//        val tvDate = bottomSheetDialog.findViewById<TextView>(R.id.tvDate)
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(bottomSheet.tvDate)
            }
        }
        bottomSheet.tvDate?.text=formattedDate
     //   bottomSheetDialog.show()
        bottomSheet.tvSave?.setOnClickListener {

                lead_interface.onOrderselection(salesId.toString(),bottomSheet.edtHeading?.text.toString(),bottomSheet.edtRemark?.text.toString(),bottomSheet.tvDate?.text.toString(),"save")

            dialog.dismiss()
        }
        bottomSheet.tvDelete?.setOnClickListener {

                lead_interface.onOrderselection(salesId.toString(),bottomSheet.edtHeading?.text.toString(),bottomSheet.edtRemark?.text.toString(),bottomSheet.tvDate?.text.toString(),"delete")

            dialog.dismiss()
        }
        bottomSheet.tvDate?.setOnClickListener {
            val mDatePicker = DatePickerDialog(
                activity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            // mDatePicker.datePicker.minDate=System.currentTimeMillis()-1000;
            mDatePicker.show()
        }
        dialog.setContentView(bottomSheet)
        dialog.show()
    }
    private fun updateDateInView(tvDate: TextView?) {
        val myFormat = "dd MMM yyyy"
        //  val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        //textDate=sdf.format(cal.getTime())
        tvDate!!.text = sdf.format(cal.getTime())
        sendFormat = "dd-MMM-yyyy" // mention the format you need
        val sdf1 = SimpleDateFormat(sendFormat, Locale.US)
        date = sdf1.format(cal.getTime())

    }

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