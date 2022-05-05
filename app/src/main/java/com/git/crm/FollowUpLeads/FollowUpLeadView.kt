package com.git.crm.FollowUpLeads

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.LoginDetails


interface FollowUpLeadView {

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