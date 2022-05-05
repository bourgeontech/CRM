package com.git.crm.CustomerDetails

import com.git.crm.Pojo.*


interface CustomerPageView {

    fun showProgress()
    fun hideProgress()
    fun District(
        login: List<DistrictList>
    )
    fun StateSuccess(data: MutableList<StateList?>)
    fun loginError(msg: String)
    fun validSucces(msg:String, data: List<LeadDetails>)
    fun validFaild(msg:String)
   fun  LocationSuccess(data: List<LocationList>)
    fun sourceSucces(data: List<SourceList>)
    fun  subsourceSucces(data: List<SubSourceList>)
    fun EmailSuccess(msg:String, data: List<LeadDetails>)
    fun EmailFaild(msg:String)
}