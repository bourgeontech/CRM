package com.git.crm.Status

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.LoginDetails
import com.git.crm.Pojo.SalesReportList
import com.git.crm.Pojo.StatusList


interface StatusView {

    fun showProgress()
    fun hideProgress()
    fun report(
        id: List<StatusList>?
    )
    fun loginError(msg: String)
   fun  onsave(message: String)
}