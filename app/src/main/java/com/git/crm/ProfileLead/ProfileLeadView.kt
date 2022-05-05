package com.git.crm.ProfileLead

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.LoginDetails
import com.git.crm.Pojo.ProfileList
import com.git.crm.Pojo.SalesReportList


interface ProfileLeadView {

    fun showProgress()
    fun hideProgress()
    fun report(
        id: List<ProfileList>?
    )
    fun loginError(msg: String)

}