package com.git.crm.Report

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.LoginDetails
import com.git.crm.Pojo.SalesReportList


interface ReportView {

    fun showProgress()
    fun hideProgress()
    fun report(
        id: List<SalesReportList>?
    )
    fun loginError(msg: String)

}