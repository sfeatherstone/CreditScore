package uk.co.wedgetech.creditscore.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import uk.co.wedgetech.creditscore.domain.network.CreditValues
import uk.co.wedgetech.creditscore.tasks.network.CreditRxService
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.creditscore.domain.network.CreditReportInfo
import uk.co.wedgetech.creditscore.domain.network.NetworkError


class CreditScoreViewModel : ViewModel() {

    private val creditRxService by lazy { CreditRxService.instance }
    val creditReport: LiveData<CreditReportInfo> by lazy { creditScoreMutable }
    private val creditScoreMutable : MutableLiveData<CreditReportInfo> = MutableLiveData()

    val error: LiveData<NetworkError> by lazy { errorMutable }
    private val errorMutable : MutableLiveData<NetworkError> = MutableLiveData()


    fun updateCreditScore() {
        val observable = creditRxService.getCredit()
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<CreditValues> {
                    override fun onSuccess(t: CreditValues) {
                        creditScoreMutable.value = t.creditReportInfo
                    }

                    override fun onError(e: Throwable) {
                        errorMutable.value = NetworkError("CreditValues", e.message)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }
                })

    }
}