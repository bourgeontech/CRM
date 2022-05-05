package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ViewLead : Serializable {
    @SerializedName("Status")
    @Expose
     val status: Boolean? = null

    @SerializedName("Message")
    @Expose
     val message: String? = null

    @SerializedName("Count")
    @Expose
     val count: Int? = null

    @SerializedName("Data")
    @Expose
     val data: List<DataList>? = null

    @SerializedName("CurrentVehicle")
    @Expose
     val currentVehicle: List<CurrentVehicle>? = null

    @SerializedName("PrefferedVehicle")
    @Expose
     val prefferedVehicle: List<PrefferedVehicle>? = null

    @SerializedName("SuggestedVehicle")
    @Expose
     val suggestedVehicle: List<SuggestedVehicle>? = null
//    @SerializedName("Status")
//    @Expose
//     val status: Boolean? = null
//
//    @SerializedName("Message")
//    @Expose
//     val message: String? = null
//
//    @SerializedName("Count")
//    @Expose
//     val count: Int? = null
//
//    @SerializedName("Data")
//    @Expose
//     val data: List<DataList>? = null
//
//    @SerializedName("CurrentVehicle")
//    @Expose
//     val currentVehicle: List<CurrentVehicle>? = null
//
//    @SerializedName("PrefferedVehicle")
//    @Expose
//     val prefferedVehicle: List<PrefferedVehicle>? = null
//
//    @SerializedName("SuggestedVehicle")
//    @Expose
//     val suggestedVehicle: List<SuggestedVehicle>? = null
}

class SuggestedVehicle: Serializable {
    @SerializedName("MakeId")
    @Expose
     val makeId: String? = null

    @SerializedName("Make")
    @Expose
     val make: String? = null

    @SerializedName("ModelId")
    @Expose
     val modelId: String? = null

    @SerializedName("Model")
    @Expose
     val model: String? = null

    @SerializedName("TestDriveNo")
    @Expose
    val TestDriveNo: String? = null


}

class PrefferedVehicle: Serializable {
    @SerializedName("MakeId")
    @Expose
     val makeId: String? = null

    @SerializedName("Make")
    @Expose
     val make: String? = null

    @SerializedName("ModelId")
    @Expose
     val modelId: String? = null

    @SerializedName("Model")
    @Expose
     val model: String? = null
}

class CurrentVehicle: Serializable {
    @SerializedName("MakeId")
    @Expose
     val makeId: String? = null

    @SerializedName("Make")
    @Expose
     val make: String? = null

    @SerializedName("ModelId")
    @Expose
     val modelId: String? = null

    @SerializedName("Model")
    @Expose
     val model: String? = null

    @SerializedName("VariantId")
    @Expose
     val variantId: String? = null

    @SerializedName("Variant")
    @Expose
     val variant: Any? = null

    @SerializedName("Year")
    @Expose
     val year: String? = null
}

class DataList: Serializable {

    @SerializedName("Id")
    @Expose
     val id: Int? = null

    @SerializedName("UserCode")
    @Expose
     val userCode: Int? = null

    @SerializedName("Name")
    @Expose
     val name: String? = null

    @SerializedName("LastName")
    @Expose
     val lastName: String? = null

    @SerializedName("Contact")
    @Expose
     val contact: String? = null

    @SerializedName("EmailId")
    @Expose
     val emailId: String? = null

    @SerializedName("Pincode")
    @Expose
     val pincode: String? = null

    @SerializedName("LocationId")
    @Expose
     val locationId: Int? = null

    @SerializedName("Location")
    @Expose
     val location: String? = null

    @SerializedName("StateId")
    @Expose
     val stateId: Int? = null

    @SerializedName("State")
    @Expose
     val state: String? = null

    @SerializedName("DistrictId")
    @Expose
     val districtId: Int? = null

    @SerializedName("District")
    @Expose
     val district: String? = null

    @SerializedName("LeadStatusId")
    @Expose
     val leadStatusId: Int? = null

    @SerializedName("LeadStatus")
    @Expose
     val leadStatus: String? = null

    @SerializedName("BranchId")
    @Expose
     val branchId: Int? = null

    @SerializedName("Branch")
    @Expose
     val branch: String? = null

    @SerializedName("AssignedtoId")
    @Expose
     val assignedtoId: Int? = null

    @SerializedName("Assignedto")
    @Expose
     val assignedto: String? = null

    @SerializedName("Remark")
    @Expose
     val remark: String? = null

    @SerializedName("EmploymentId")
    @Expose
     val employmentId: Int? = null

    @SerializedName("Employment")
    @Expose
     val employment: String? = null

    @SerializedName("AnualIncome")
    @Expose
     val anualIncome: String? = null

    @SerializedName("Budget")
    @Expose
     val budget: String? = null

    @SerializedName("LeadDate")
    @Expose
     val leadDate: String? = null

    @SerializedName("PlantoBuyId")
    @Expose
     val plantoBuyId: Int? = null

    @SerializedName("PlantoBuy")
    @Expose
     val plantoBuy: String? = null

    @SerializedName("SourceId")
    @Expose
     val sourceId: Int? = null

    @SerializedName("Source")
    @Expose
     val source: String? = null

    @SerializedName("SubSourceId")
    @Expose
     val subSourceId: Int? = null

    @SerializedName("SubSource")
    @Expose
     val subSource: String? = null

    @SerializedName("TestDriveNo")
    @Expose
     val testDriveNo: Any? = null
//    @SerializedName("Id")
//    @Expose
//     val id: Int? = null
//
//    @SerializedName("UserCode")
//    @Expose
//     val userCode: Int? = null
//
//    @SerializedName("Name")
//    @Expose
//     val name: String? = null
//
//    @SerializedName("LastName")
//    @Expose
//     val lastName: String? = null
//
//    @SerializedName("Contact")
//    @Expose
//     val contact: String? = null
//
//    @SerializedName("EmailId")
//    @Expose
//     val emailId: String? = null
//
//    @SerializedName("Pincode")
//    @Expose
//     val pincode: String? = null
//
//    @SerializedName("LocationId")
//    @Expose
//     val locationId: Int? = null
//
//    @SerializedName("Location")
//    @Expose
//     val location: Any? = null
//
//    @SerializedName("StateId")
//    @Expose
//     val stateId: Int? = null
//
//    @SerializedName("State")
//    @Expose
//     val state: String? = null
//
//    @SerializedName("DistrictId")
//    @Expose
//     val districtId: Int? = null
//
//    @SerializedName("District")
//    @Expose
//     val district: String? = null
//
//    @SerializedName("LeadStatusId")
//    @Expose
//     val leadStatusId: Int? = null
//
//    @SerializedName("LeadStatus")
//    @Expose
//     val leadStatus: String? = null
//
//    @SerializedName("BranchId")
//    @Expose
//     val branchId: Int? = null
//
//    @SerializedName("Branch")
//    @Expose
//     val branch: String? = null
//
//    @SerializedName("AssignedtoId")
//    @Expose
//     val assignedtoId: Int? = null
//
//    @SerializedName("Assignedto")
//    @Expose
//     val assignedto: String? = null
//
//    @SerializedName("Remark")
//    @Expose
//     val remark: String? = null
//
//    @SerializedName("EmploymentId")
//    @Expose
//     val employmentId: Int? = null
//
//    @SerializedName("Employment")
//    @Expose
//     val employment: String? = null
//
//    @SerializedName("AnualIncome")
//    @Expose
//     val anualIncome: Double? = null
//
//    @SerializedName("Budget")
//    @Expose
//     val budget: Double? = null
//
//    @SerializedName("LeadDate")
//    @Expose
//     val leadDate: String? = null
//
//    @SerializedName("PlantoBuyId")
//    @Expose
//     val plantoBuyId: Int? = null
//
//    @SerializedName("PlantoBuy")
//    @Expose
//     val plantoBuy: String? = null
//
//    @SerializedName("SourceId")
//    @Expose
//     val sourceId: Int? = null
//
//    @SerializedName("Source")
//    @Expose
//     val source: String? = null
//
//    @SerializedName("SubSourceId")
//    @Expose
//     val subSourceId: Int? = null
//
//    @SerializedName("SubSource")
//    @Expose
//     val subSource: String? = null
}
