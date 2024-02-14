package com.example.severdrivenuiexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.severdrivenuiexample.core.AssetReader
import com.example.severdrivenuiexample.databinding.ActivityMainBinding
import com.yandex.div.DivDataTag
import com.yandex.div.core.Div2Context
import com.yandex.div.core.DivActionHandler
import com.yandex.div.core.DivConfiguration
import com.yandex.div.core.DivViewFacade
import com.yandex.div2.DivAction

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavigation()
//        val divJson = assetReader.read("main_feed.json")
//        val templates = divJson.getJSONObject("templates")
//        val card = divJson.getJSONObject("card")
//        val environment = DivParsingEnvironment(ParsingErrorLogger.LOG)
//        environment.parseTemplates(templates)
//        val divDate =  DivData(environment, card)
//        val divView = Div2View(Div2Context(baseContext = this, configuration = createDivConfiguration()))
//        findViewById<FrameLayout>(R.id.sample_ui).addView(divView)
//        divView.setData(divDate, DivDataTag("hello"))
    }


    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        navController.graph = graph
        NavigationUI.setupWithNavController(findViewById(R.id.toolbar), navController, null)
    }





//   view private fun startActivityAction(klass: Class<out Activity>) {
//        startActivity()
//        startActivity(this, Intent(this, klass), null)
//    }
}