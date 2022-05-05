package com.git.crm.ViewLead

import com.git.crm.Pojo.*


interface ViewLeadView {

    fun showProgress()
    fun hideProgress()
    fun report(
        data: ViewLead?
    )
    fun loginError(msg: String)

}