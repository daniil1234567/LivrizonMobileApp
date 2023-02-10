package com.app.livrizon.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.livrizon.R
import com.app.livrizon.databinding.ActivitySecondaryBinding
import com.app.livrizon.function.log
import com.app.livrizon.values.Parameters

abstract class CustomActivity : AppCompatActivity() {
    lateinit var navController: NavController
    var key: Int = 0
    private lateinit var binding: ActivitySecondaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        key = intent.getIntExtra(Parameters.key, 0)
        init()
    }

    abstract fun getStartDestination(): Int
    open fun getGraph(): Int {
        return R.navigation.main_graph
    }

    open fun setArgs(): Bundle {
        return Bundle().apply {
            if (intent.extras != null) putAll(intent.extras)
        }
    }

    open fun init() {
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        navController =
            (supportFragmentManager.findFragmentById(binding.frame.id) as NavHostFragment).navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(getGraph())
        val startDestination = getStartDestination()
        graph.setStartDestination(startDestination)
        navController.setGraph(graph, setArgs())
    }
}