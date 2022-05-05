package com.git.crm.Dashboard

import com.git.crm.Pojo.DashboardList


class DashboardPresenter(var loginview: DashboardView?, val loginInteractor: DashboardInteractor) :
    DashboardInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<DashboardList>?
    ) {
        loginview?.apply {
            navigateToHome(id)
            hideProgress()
        }
    }

    fun getDashboard(id: String, name: String) {
        loginview?.showProgress()
        loginInteractor.dashboard(id,name, this)
    }

    fun onDestroy() {
        loginview = null
    }
}