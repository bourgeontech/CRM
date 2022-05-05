package com.git.crm.CustomerDetails

import com.git.crm.Pojo.*


class CustomerPageOnePresenter(var loginview: CustomerPageView?, val loginInteractor: CustomerPageOneInteractor) :
    CustomerPageOneInteractor.OnLoginFinishedListener {
    override fun onSourceSuccess(data: List<SourceList>) {
        loginview?.apply {
            sourceSucces(data)
            hideProgress()
        }
    }

    override fun onSubSourceSuccess(data: List<SubSourceList>) {
        loginview?.apply {
            subsourceSucces(data)
            hideProgress()
        }
    }

    override fun onValiSuccess(msg: String, data: List<LeadDetails>) {
        loginview?.apply {
            validSucces(msg,data)
            hideProgress()
        }
    }

    override fun onValidFailure(msg: String) {
        loginview?.apply {
            validFaild(msg)
            hideProgress()
        }
    }

    override fun onEmailSuccess(msg: String, data: List<LeadDetails>) {
        loginview?.apply {
            EmailSuccess(msg,data)
            hideProgress()
        }
    }

    override fun onEmailFailure(msg: String) {
        loginview?.apply {
            EmailFaild(msg)
            hideProgress()
        }
    }

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onStateSuccess(data: MutableList<StateList?>) {
        loginview?.apply {
            StateSuccess(data)
            hideProgress()
        }
    }

    override fun onLocationSuccess(data: List<LocationList>) {
        loginview?.apply {
            LocationSuccess(data)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<DistrictList>
    ) {
        loginview?.apply {
            District(id)
            hideProgress()
        }
    }

    fun getDistrict(stateid: String) {
        loginview?.showProgress()
        loginInteractor.login(stateid, this)
    }

    fun getState() {
        loginview?.showProgress()
        loginInteractor.state( this)
    }
    fun getLocation(stateid: String,districtis: String) {
        loginview?.showProgress()
        loginInteractor.location(stateid,districtis, this)
    }
    fun getSorce() {
        loginview?.showProgress()
        loginInteractor.source(this)
    }

    fun getSubSorce(sourceid: String) {
        loginview?.showProgress()
        loginInteractor.subsource(sourceid,this)
    }

    fun getValid(s: String,field: String) {
        loginview?.showProgress()
        loginInteractor.valid(s,field,this)
    }


}