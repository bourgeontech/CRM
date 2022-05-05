package com.git.crm.Followuptask

import com.git.crm.Pojo.FollowUpList
import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.HeadingList


class FollowUpTaskPresenter(var loginview: FollowUpTaskView?, val loginInteractor: PollowUpTaskInteractor) :
    PollowUpTaskInteractor.OnLoginFinishedListener {
    override fun onSuccess( id: List<FollowUpList?>) {
        loginview?.apply {
            Lead(id)
            hideProgress()
        }
    }

//    override fun onSuccessMore( id: MutableList<GetLeadsPojo?>) {
//        loginview?.apply {
//            LeadMore(id)
//            hideProgress()
//        }
//    }

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onAdd(msg: String) {
        loginview?.apply {
            onFollowupAdd(msg)
            hideProgress()
        }
    }

    override fun onHeading(data: List<HeadingList>) {
        loginview?.apply {
            onHeadingSuccess(data)
            hideProgress()
        }
    }

    override fun onFailureMore(msg: String) {
        loginview?.apply {
//            loginError(msg)
              hideProgress()
        }
    }

    fun lead(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String) {
        loginview?.showProgress()
        loginInteractor.lead(userId, LeadStatus,FromDate,ToDate,Status,PageIndex,PageSize,Keyword, this)
    }

    fun leadmore(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String) {
        loginview?.showProgress()
        loginInteractor.leadmore(userId, LeadStatus,FromDate,ToDate,Status,PageIndex,PageSize,Keyword, this)
    }

    fun savefollow(userId: String, leadid: String, heading: String, remark: String, date: String) {
        loginview?.showProgress()
        loginInteractor.save(userId,leadid,heading,remark,date,this)
    }
    fun deletefollow(followupid: String) {
        loginview?.showProgress()
        loginInteractor.delete(followupid,this)
    }

    fun getHeading() {
        loginview?.showProgress()
        loginInteractor.heading(this)
    }


}