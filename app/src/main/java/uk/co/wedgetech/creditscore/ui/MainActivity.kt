package uk.co.wedgetech.creditscore.ui

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import android.view.View.VISIBLE
import uk.co.wedgetech.creditscore.ui.widgets.CircleAngleAnimation

import kotlinx.android.synthetic.main.activity_main.*
import uk.co.wedgetech.creditscore.R
import uk.co.wedgetech.creditscore.domain.network.CreditReportInfo


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model : CreditScoreViewModel = ViewModelProviders.of(this).get(CreditScoreViewModel::class.java)
        val creditReportObserver = object: Observer<CreditReportInfo> {
            override fun onChanged(creditReportInfo: CreditReportInfo?) {
                if (creditReportInfo!=null) {
                    updateScreen(creditReportInfo)
                }
            }
        }
        model.creditReport.observe(this, creditReportObserver)

        //Fetch new score
        model.updateCreditScore()
    }

    fun updateScreen(creditReportInfo: CreditReportInfo) {
        inner_layout.visibility = VISIBLE
        val animation = CircleAngleAnimation(score_circle, (360 * (creditReportInfo.score-creditReportInfo.minScoreValue)
                / (creditReportInfo.maxScoreValue-creditReportInfo.minScoreValue)))
        animation.setDuration(1000)
        score_circle.startAnimation(animation)
        out_of_text.text = getString(R.string.out_of_text).format(creditReportInfo.maxScoreValue)
        score_text.text = creditReportInfo.score.toString()
    }
}
