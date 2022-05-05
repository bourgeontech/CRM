package com.git.crm.Status

import com.git.crm.Pojo.ProfileList
import com.git.crm.Pojo.StatusList


class StatusPresenter(var loginview: StatusView?, val loginInteractor: StatusInteractor) :
    StatusInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onsave(message: String) {
        loginview?.apply {
            onsave(message)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<StatusList>
    ) {
        loginview?.apply {
            report(id)
            hideProgress()
        }
    }

    fun report(userid: String,status:String) {
        loginview?.showProgress()
        loginInteractor.login(userid,status, this)
    }

    fun savestatus(leadid: String, userId: String, statusid: String,remark: String) {
        loginview?.showProgress()
        loginInteractor.savestatus(leadid,userId,statusid,remark, this)
    }


}