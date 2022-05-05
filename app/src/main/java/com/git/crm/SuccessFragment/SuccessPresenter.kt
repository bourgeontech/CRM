package com.git.crm.SuccessFragment

import com.git.crm.Pojo.HeadingList
import com.git.crm.Pojo.ProfileList
import com.git.crm.Pojo.StatusList


class SuccessPresenter(var loginview: SuccessView?, val loginInteractor: SuccessInteractor) :
    SuccessInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }
    override fun onHeading(data: List<HeadingList>) {
        loginview?.apply {
            onHeadingSuccess(data)
            hideProgress()
        }
    }

    override fun onAdd(msg: String) {
        loginview?.apply {
            onFollowupAdd(msg)
            hideProgress()
        }
    }
    fun savefollow(userId: String, leadid: String, heading: String, remark: String, date: String) {
        loginview?.showProgress()
        loginInteractor.save(userId,leadid,heading,remark,date,this)
    }

    fun getHeading() {
        loginview?.showProgress()
        loginInteractor.heading(this)
    }
}