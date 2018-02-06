package uk.co.wedgetech.creditscore.tasks.network

import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import uk.co.wedgetech.creditscore.domain.network.CreditValues


class CreditRxService() {

    private val clearscoreRestApi: ClearscoreRestAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/prod/mockcredit/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        clearscoreRestApi = retrofit.create<ClearscoreRestAPI>(ClearscoreRestAPI::class.java)
    }

    fun getCredit(): Observable<CreditValues> {
        return clearscoreRestApi.loadValues();
    }
}