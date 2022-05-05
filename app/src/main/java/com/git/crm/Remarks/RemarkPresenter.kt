package com.git.crm.Remarks

import com.git.crm.Pojo.ProfileList
import com.git.crm.Pojo.RemarkList
import com.git.crm.Pojo.StatusList


class RemarkPresenter(var loginview: RemarkView?, val loginInteractor: RemarkInteractor) :
    RemarkInteractor.OnLoginFinishedListener {

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
        id: List<RemarkList>
    ) {
        loginview?.apply {
            report(id)
            hideProgress()
        }
    }

    fun Remark(userid: String,status:String) {
        loginview?.showProgress()
        loginInteractor.remark(userid,status, this)
    }

    fun saveRemark(leadid: String, userId: String, statusid: String) {
        loginview?.showProgress()
        loginInteractor.saveremark(leadid,userId,statusid, this)
    }


}