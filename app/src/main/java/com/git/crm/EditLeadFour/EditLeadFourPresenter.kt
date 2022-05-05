package com.git.crm.EditLeadFour

import androidx.fragment.app.FragmentActivity
import com.git.crm.Pojo.*
import org.json.JSONArray


class EditLeadFourPresenter(var loginview: EditLeadFourView?, val loginInteractor: EditLeadFourInteractor) :
    EditLeadFourInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onVariantSuccess(data: List<UserList>) {
        loginview?.apply {
            VariantSuccess(data)
            hideProgress()
        }
    }

    override fun onModelSuccess(data: List<BranchList>) {
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

    override fun onSendSuccess(message: String) {
        loginview?.apply {
            sendSucces(message)
            hideProgress()
        }
    }


    override fun onMakeSuccess(
        id: List<LeadList>
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


    fun getMake() {
        loginview?.showProgress()
        loginInteractor.make(this)
    }

    fun getMake2(type:String) {
        loginview?.showProgress()
        loginInteractor.make2(type,this)
    }

    fun getVariant(userId: String) {
        loginview?.showProgress()
        loginInteractor.variant( userId,this)
    }
    fun getModel() {
        loginview?.showProgress()
        loginInteractor.model( this)
    }

    fun getModel2(makeid:String,type:String) {
        loginview?.showProgress()
        loginInteractor.model2(type,makeid, this)
    }
    fun getSend(
        userId: String,
        name: String,
        email: String,
        phone: String,
        pincode: String,
        state: String,
        district: String,
        location: String,
        employment: String,
        income: String,
        budjet: String,
        leaddate: String,
        leadloginid: String,
        arrayone: JSONArray,
        array: JSONArray,
        array3: JSONArray,
        branchid: String,
        leadstatusid: String,
        leadassignedid: String,
        remark: String,
        plantobuyid: String?,
        lastname: String?,
        id: String?,
        sourceid: String?,
        subsourceid: String?,
        vehiclenumber: String?,
        activity: FragmentActivity
    ) {
        loginview?.showProgress()
        loginInteractor.send(userId, name,email,
            phone,
            pincode,
            state,
            district,
            location,
            employment,
            income,
            budjet,
            leaddate,
            leadloginid,
            arrayone,
            array,array3,branchid,leadstatusid,leadassignedid,remark,plantobuyid,lastname,id,sourceid,subsourceid,vehiclenumber,activity,this)
    }

    fun getSorce() {
        loginview?.showProgress()
        loginInteractor.source(this)
    }

    fun getSubSorce(sourceid: String) {
        loginview?.showProgress()
        loginInteractor.subsource(sourceid,this)
    }

}