package com.git.crm.Leads

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.LoginDetails
import com.git.crm.Pojo.ViewLead


interface LeadView {

    fun showProgress()
    fun hideProgress()
    fun Lead(
        id: MutableList<GetLeadsPojo?>
    )
    fun loginError(msg: String)
    fun LeadMore(
        id: MutableList<GetLeadsPojo?>
    )
   fun leadDetails(body: ViewLead)
}