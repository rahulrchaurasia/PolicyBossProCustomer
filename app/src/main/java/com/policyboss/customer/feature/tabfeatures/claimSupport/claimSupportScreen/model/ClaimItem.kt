package com.policyboss.customer.feature.tabfeatures.claimSupport.claimSupportScreen.model

//API  REsponse
/*
{
  "claimId": "CLM10001",
  "policyNumber": "PB-2025-123456",
  "productName": "Car Insurance",
  "claimNumber": "CI987654",
  "claimStatus": "UNDER_REVIEW",
  "claimAmount": 45000,
  "incidentDate": "2026-07-10",
  "filedDate": "2026-07-11"
}
 */
data class ClaimItem(

    val claimId: String,

    val policyNumber: String,

    val productName: String,

    val claimNumber: String,

    val claimStatus: ClaimStatus,

    val claimAmount: Double?,

    val incidentDate: String,

    val filedDate: String

)