package uk.co.wedgetech.creditscore.tasks.network

import io.reactivex.Observable
import retrofit2.http.GET
import uk.co.wedgetech.creditscore.domain.network.CreditValues


interface ClearscoreRestAPI {
    @GET("values/")
    fun loadValues(): Observable<CreditValues>
}