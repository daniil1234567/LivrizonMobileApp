package com.app.livrizon.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.app.livrizon.R
import com.app.livrizon.databinding.ActivitySecondaryBinding
import com.app.livrizon.function.lightStatusBar
import com.app.livrizon.function.log
import com.app.livrizon.values.Parameters

class SecondaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondaryBinding
    val i = Intent()
    var key = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        lightStatusBar(window)
        key = intent.getIntExtra(Parameters.key, 0)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.frame) as NavHostFragment).navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.main_graph)
        val startDestination =
            when (key) {
                //SUBSCRIPTIONS, FOLLOWERS, CONNECTIONS -> {
                //    when (key) {
                //        SUBSCRIPTIONS -> intent.putExtra(Parameters.title, "Подписки")
                //        FOLLOWERS -> intent.putExtra(Parameters.title, "Подписчики")
                //        CONNECTIONS -> intent.putExtra(Parameters.title, "Связи")
                //    }
                //    R.id.profileListWithToolbarFragment
                //}
                //ARTICLES -> R.id.articlesFragment
                MESSENGER -> R.id.messengerFragment
                CHAT -> R.id.chatFragment
                PAGE -> R.id.pageShimmer
                MY_CONNECTIONS -> R.id.connectionFragment
                CREATE_POST -> R.id.createPostFragment
                CREATE_ARTICLE -> R.id.createArticleFragment
                else -> 0
            }
        graph.setStartDestination(startDestination)
        navController.setGraph(graph, intent.extras)

    }

    companion object {
        const val PAGE = 0
        const val CREATE_PUBLICATION = 1
        const val CHAT = 2
        const val SUBSCRIPTIONS = 3
        const val CONNECTIONS = 4
        const val CREATE_PUBLIC = 7
        const val POSSIBLE_ACCOUNTS = 8
        const val PROFILES_RECOMMENDATION = 9
        const val PROFILES_POPULAR = 10
        const val FIND_PROFILES = 11
        const val CREATE_POST = 12
        const val CREATE_ARTICLE = 13
        const val FOLLOWERS = 14
        const val CHOOSE = 15
        const val MY_CONNECTIONS = 16
        const val MEMBER = 17
        const val LIKE = 18
        const val MESSENGER = 19
    }
}