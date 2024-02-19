package com.example.severdrivenuiexample.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.severdrivenuiexample.PicassoDivImageLoader
import com.example.severdrivenuiexample.core.AssetReader
import com.example.severdrivenuiexample.core.Div2ViewFactory
import com.example.severdrivenuiexample.databinding.FragmentShowDivKitsBinding
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



private const val FRAGMENT_PAYMENTS = "payment"
private const val FRAGMENT = "fragment"
private const val FRAGMENT_QR = "qr"


class ShowDivKitsFragment : Fragment() {

    private lateinit var binding: FragmentShowDivKitsBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowDivKitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val assetReader = AssetReader(requireContext())
        val divJson = assetReader.read("main_feed.json")
        val templates = divJson.getJSONObject("templates")
        val card = divJson.getJSONObject("card")
        val environment = DivParsingEnvironment(ParsingErrorLogger.LOG)
        environment.parseTemplates(templates)
        val divDate = DivData(environment, card)
        val divView = Div2ViewFactory(Div2Context(baseContext = requireContext() as ContextThemeWrapper, configuration = createDivConfiguration()),templates).createView(card)
        binding.container.addView(divView)
        divView.setData(divDate, DivDataTag("hello"))
    }

    private fun createDivConfiguration(): DivConfiguration {
        return DivConfiguration.Builder(PicassoDivImageLoader(requireContext() as ContextThemeWrapper,))
            .actionHandler(DemoDivActionHandler(findNavController(),requireContext()))
            .supportHyphenation(true)
            .visualErrorsEnabled(true)
            .build()
    }
    class DemoDivActionHandler(private val nav:NavController, val context: Context) : DivActionHandler() {
        override fun handleAction(action: DivAction, view: DivViewFacade): Boolean {
            if (action.url == null) return false
            val uri = action.url!!.evaluate(view.expressionResolver)
            val links = uri.getQueryParameter(FRAGMENT)
            nav.navigate(Uri.parse("com.example.severdrivenuiexample://${links}/"))
            return super.handleAction(action, view)
        }}
}