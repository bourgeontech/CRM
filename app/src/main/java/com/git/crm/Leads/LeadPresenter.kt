package com.git.crm.Leads

import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.ViewLead


class LeadPresenter(var loginview: LeadView?, val loginInteractor: LeadInteractor) :
    LeadInteractor.OnLoginFinishedListener {
    override fun onSuccess( id: MutableList<GetLeadsPojo?>) {
        loginview?.apply {
            Lead(id)
            hideProgress()
        }
    }

    override fun onSuccessMore( id: MutableList<GetLeadsPojo?>) {
        loginview?.apply {
            LeadMore(id)
            hideProgress()
        }
    }

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onLeadDetailsSuccess(body: ViewLead) {
        loginview?.apply {
            leadDetails(body)
            hideProgress()
        }
    }

    override fun onFailureMore(msg: String) {
        loginview?.apply {
//            loginError(msg)
              hideProgress()
        }
    }

    fun lead(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String,DateType: String) {
        loginview?.showProgress()
        loginInteractor.lead(userId, LeadStatus,FromDate,ToDate,Status,PageIndex,PageSize,Keyword,DateType, this)
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

    fun leaddetails(userId: String, leadid: String) {
        loginview?.showProgress()
        loginInteractor.leaddetails(userId,leadid,this)
    }


}