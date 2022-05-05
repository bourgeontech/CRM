package com.git.crm.Networks

import com.git.crm.Pojo.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @POST("login")
    fun userLogin(@Body params: RequestBody?): Call<LogiPojo>

    @POST("get-district")
    fun getDistrict(@Body params: RequestBody?): Call<DistrictPojo>

    @POST("get-state")
    fun getState(): Call<StatePojo>

    @POST("get-location")
    fun getLocation(@Body params: RequestBody?): Call<LocationPojo>

    @POST("get-variant")
    fun getVariant(@Body params: RequestBody?): Call<VariantPojo>

    @POST("get-model")
    fun getModel(@Body params: RequestBody?): Call<ModelPojo>

    @POST("get-make")
    fun getMake(): Call<MakePojo>

    @POST("get-leadstatus")
    fun getLead(): Call<LeadPojo>

    @POST("get-branch")
    fun getBranch(): Call<BranchPojo>

    @POST("get-users")
    fun getUser(@Body params: RequestBody?): Call<UserPojo>

    @POST("get-dashboard")
    fun getDashboard(@Body params: RequestBody?): Call<Dashboard>

    @POST("save-lead")
    fun sendDate(@Body params: RequestBody?): Call<SendPojo>

    @POST("get-leads")
    fun getLeads(@Body params: RequestBody?): Call<GetLeadPojo>


    @POST("get-leads-followup")
    fun get_leads_followup(@Body params: RequestBody?): Call<GetLeadPojo>
    @POST("get-employement")
    fun getEmployment(): Call<EmploymentPojo>

    @POST("get-plantobuy")
    fun getPlan(): Call<PlanToBuyPojo>

    @POST("get-year")
    fun getYear(): Call<YearPojo>

    @POST("get-sales-report")
    fun getReport(@Body params: RequestBody?): Call<SalesReportPojo>

    @POST("save-followup")
    fun savefollowup(@Body params: RequestBody?): Call<SendPojo>

    @POST("delete-followup")
    fun deletefollowup(@Body params: RequestBody?): Call<SendPojo>

    @POST("get-followup")
    fun getFollowUp(@Body params: RequestBody?): Call<FollowUpPojo>

    @POST("get-lead-profile")
    fun getProfile(@Body params: RequestBody?): Call<ProfilePojo>

    @POST("get-status")
    fun getStatus(@Body params: RequestBody?): Call<StatusPojo>

    @POST("save-status")
    fun saveStatus(@Body params: RequestBody?): Call<SendPojo>

    @POST("save-remark")
    fun saveREmark(@Body params: RequestBody?): Call<SendPojo>

    @POST("get-remark")
    fun getRemark(@Body params: RequestBody?): Call<RemarkPojo>

    @POST("get-deal")
    fun getDeal(@Body params: RequestBody?): Call<DealPojo>

    @POST("save-deal")
    fun saveDeal(@Body params: RequestBody?): Call<SendPojo>

    @POST("get-lead-details")
    fun getLeadDetails(@Body params: RequestBody?): Call<ViewLead>

    @POST("get-source")
    fun getSource(): Call<SourcePojo>

    @POST("get-subsource")
    fun getSubSource(@Body params: RequestBody?): Call<SuSourcePojo>

    @POST("check-lead")
    fun valid(@Body params: RequestBody?): Call<SendPojoTwo>

    @POST("get-user-profile")
    fun getUserProfile(@Body params: RequestBody?): Call<UserProfilePojo>

    @POST("logout")
    fun userLogout(@Body params: RequestBody?): Call<LogOutPojo>

    @POST("get-income-range")
    fun get_income_range(): Call<IncomePojo>

    @POST("get-budget-range")
    fun get_budget_range(): Call<IncomePojo>

    @POST("get-followup-heading")
    fun get_followup_heading(): Call<HeadingPojo>
}


