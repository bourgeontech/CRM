package com.git.crm.EditLeadOne

import com.git.crm.Pojo.*


interface EditLeadOneView {

    fun showProgress()
    fun hideProgress()
    fun District(
        login: List<DistrictList>
    )
    fun StateSuccess(data: MutableList<StateList?>)
    fun loginError(msg: String)
   fun  LocationSuccess(data: List<LocationList>)
    fun sourceSucces(data: List<SourceList>)
    fun  subsourceSucces(data: List<SubSourceList>)
}