package com.git.crm.Followuptask

import com.git.crm.Pojo.FollowUpList
import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.HeadingList
import com.git.crm.Pojo.LoginDetails


interface FollowUpTaskView {

    fun showProgress()
    fun hideProgress()
    fun Lead(
        id: List<FollowUpList?>
    )
    fun loginError(msg: String)
    fun onFollowupAdd(msg:String)
    fun onHeadingSuccess(data: List<HeadingList>)
//    fun LeadMore(
//        id: MutableList<GetLeadsPojo?>
//    )
}