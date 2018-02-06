package uk.co.wedgetech.creditscore.domain.network

/***
 * Model for inner value of return from `values/`. All fields not need are ignored.
 */
data class CreditReportInfo(val score: Int, val maxScoreValue: Int, val minScoreValue: Int)