package com.example.severdrivenuiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.severdrivenuiexample.core.AssetReader
import com.yandex.div.DivDataTag
import com.yandex.div.core.Div2Context
import com.yandex.div.core.DivActionHandler
import com.yandex.div.core.DivConfiguration
import com.yandex.div.core.DivViewFacade
import com.yandex.div.core.view2.Div2View
import com.yandex.div.data.DivParsingEnvironment
import com.yandex.div.json.ParsingErrorLogger
import com.yandex.div2.DivAction
import com.yandex.div2.DivData

class MainActivity : AppCompatActivity() {
    private val assetReader = AssetReader(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val divJson = assetReader.read("main_feed.json")
        val templates = divJson.getJSONObject("templates")
        val card = divJson.getJSONObject("card")
        val environment = DivParsingEnvironment(ParsingErrorLogger.LOG)
        environment.parseTemplates(templates)
        val divDate =  DivData(environment, card)
        val divView = Div2View(Div2Context(baseContext = this, configuration = createDivConfiguration()))
        divView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT,
        )
        findViewById<ConstraintLayout>(R.id.container).addView(divView)
        divView.setData(divDate, DivDataTag("hello"))
    }




    private fun createDivConfiguration(): DivConfiguration {
        return DivConfiguration.Builder(PicassoDivImageLoader(this))
            .actionHandler(DemoDivActionHandler())
            .supportHyphenation(true)
            .visualErrorsEnabled(true)
            .build()
    }
    class DemoDivActionHandler: DivActionHandler() {
        override fun handleAction(action: DivAction, view: DivViewFacade): Boolean {
            Log.d("value","${action.logId}")
            return super.handleAction(action, view)
        }
    }
}