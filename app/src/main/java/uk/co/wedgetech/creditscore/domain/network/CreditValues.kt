package uk.co.wedgetech.creditscore.domain.network

/***
 * Represents the return structure from `values/`. All un-required values are ignore/discarded
 */
data class CreditValues(val creditReportInfo: CreditReportInfo?)