package uk.co.wedgetech.creditscore.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import uk.co.wedgetech.creditscore.domain.network.CreditValues
import uk.co.wedgetech.creditscore.tasks.network.CreditRxService
import android.content.ContentValues.TAG
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.creditscore.domain.network.CreditReportInfo


class CreditScoreViewModel(): ViewModel() {

    private val creditRxService by lazy { CreditRxService() }
    val creditReport: LiveData<CreditReportInfo> by lazy { creditScoreMutable }
    private val creditScoreMutable : MutableLiveData<CreditReportInfo> = MutableLiveData()

    fun updateCreditScore() {
        //val creditRxService = CreditRxService()
        val observable = creditRxService.getCredit()
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CreditValues> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(s: CreditValues) {
                        creditScoreMutable.value = s.creditReportInfo
                    }

                    override fun onError(e: Throwable) {
                        //TODO handle error
                        Log.i(TAG, "onError: ")
                    }

                    override fun onComplete() {
                    }
                })

    }
}