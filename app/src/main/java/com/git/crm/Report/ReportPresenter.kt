package com.git.crm.Report

import com.git.crm.Pojo.LoginDetails
import com.git.crm.Pojo.SalesReportList


class ReportPresenter(var loginview: ReportView?, val loginInteractor: ReportInteractor) :
    ReportInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<SalesReportList>?
    ) {
        loginview?.apply {
            report(id)
            hideProgress()
        }
    }

    fun report(userid: String) {
        loginview?.showProgress()
        loginInteractor.login(userid, this)
    }

    fun onDestroy() {
        loginview = null
    }
}