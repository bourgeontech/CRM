package com.git.crm.CustomerDetailsThree

import com.git.crm.Pojo.*


class CustomerPageThreePresenter(var loginview: CustomerPageThreeView?, val loginInteractor: CustomerPageThreeInteractor) :
    CustomerPageThreeInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onVariantSuccess(data: List<VariantList>) {
        loginview?.apply {
            VariantSuccess(data)
            hideProgress()
        }
    }

    override fun onModelSuccess(data: List<ModelList>) {
        loginview?.apply {
            ModelSuccess(data)
            hideProgress()
        }
    }

    override fun onModelSuccessTwo(data: List<ModelList>,type:String) {
        loginview?.apply {
            ModelSuccessTwo(data,type)
            hideProgress()
        }
    }

    override fun onYearSuccess(data: List<YearList>) {
        loginview?.apply {
            YearSuccess(data)
            hideProgress()
        }
    }
    override fun onIncomeSuccess(data: List<IncomeList>) {
        loginview?.apply {
            IncomeSuccess(data)
            hideProgress()
        }
    }


    override fun onMakeSuccess(
        id: List<MakeList>
    ) {
        loginview?.apply {
            Make(id)
            hideProgress()
        }
    }

    override fun onMakeSuccess2(
        id: List<MakeList>,type:String
    ) {
        loginview?.apply {
            Make2(id,type)
            hideProgress()
        }
    }

    fun getMake() {
        loginview?.showProgress()
        loginInteractor.make(this)
    }

    fun getMake2(type:String) {
        loginview?.showProgress()
        loginInteractor.make2(type,this)
    }

    fun getVariant(modelid:String) {
        loginview?.showProgress()
        loginInteractor.variant(modelid, this)
    }
    fun getModel(makeid: String) {
        loginview?.showProgress()
        loginInteractor.model(makeid, this)
    }

    fun getModel2(makeid:String,type:String) {
        loginview?.showProgress()
        loginInteractor.model2(type, makeid  ,this)
    }

    fun getYear() {
        loginview?.showProgress()
        loginInteractor.year(this)
    }

    fun getBudjet() {
        loginview?.showProgress()
        loginInteractor.budjet( this)
    }

}