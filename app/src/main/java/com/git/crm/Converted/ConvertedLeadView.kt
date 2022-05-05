package com.git.crm.Converted

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.LoginDetails


interface ConvertedLeadView {

    fun showProgress()
    fun hideProgress()
    fun Lead(
        id: MutableList<GetLeadsPojo?>
    )
    fun loginError(msg: String)
    fun LeadMore(
        id: MutableList<GetLeadsPojo?>
    )
}