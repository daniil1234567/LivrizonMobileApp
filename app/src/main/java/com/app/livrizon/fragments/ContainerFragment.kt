package com.app.livrizon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.app.livrizon.R.id.*
import com.app.livrizon.R.navigation.main_graph
import com.app.livrizon.activities.MainActivity
import com.app.livrizon.activities.MainActivity.Companion.CATEGORY
import com.app.livrizon.activities.MainActivity.Companion.HOME
import com.app.livrizon.databinding.FragmentContainerBinding
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.token

class ContainerFragment(val key: Int) : Fragment() {
    lateinit var binding: FragmentContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariable()
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    fun init() {
        val navHostFragment = childFragmentManager.findFragmentById(binding.frame.id)
                as NavHostFragment
        val graph = navHostFragment.navController
            .navInflater.inflate(main_graph)
        val bundle = Bundle()
        graph.setStartDestination(
            when (key) {
                HOME -> {
                    bundle.putSerializable(
                        Parameters.posts,
                        (requireActivity() as MainActivity).posts
                    )
                    homeFragment
                }
                CATEGORY -> {
                    categoryFragment
                }
                else -> {
                    bundle.apply {
                        putInt(Parameters.profile_id, (token as AccessToken).id)
                    }
                    pageShimmer
                }
            }
        )
        navHostFragment.findNavController()
            .setGraph(graph, bundle)

    }

    fun initVariable() {
        binding = FragmentContainerBinding.inflate(layoutInflater)
    }

}