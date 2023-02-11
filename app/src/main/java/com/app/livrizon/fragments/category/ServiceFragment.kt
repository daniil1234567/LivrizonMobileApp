package com.app.livrizon.fragments.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.R
import com.app.livrizon.activities.ServiceActivity
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.MoveImpl
import com.app.livrizon.adapter.RecommendationAdapter
import com.app.livrizon.adapter.ServiceAdapter
import com.app.livrizon.databinding.FragmentServiceBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.Recommendation
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.service.Service
import com.app.livrizon.request.Filter
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.security.Role
import com.app.livrizon.values.Parameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ServiceFragment : CustomFragment(), MoveImpl {
    lateinit var binding: FragmentServiceBinding
    lateinit var serviceAdapter: ServiceAdapter
    lateinit var serviceRecyclerView: RecyclerView
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun moveToWall(current: Base) {
        navController.navigate(R.id.action_categoryFragment_to_pageShimmer, Bundle().apply {
            putInt(Parameters.profile_id, current.equals())
        })
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : RecommendationAdapter(requireContext(), this) {
            override fun moveToWall(current: Base) {
                this@ServiceFragment.moveToWall(current)
            }
        }
        serviceAdapter = object : ServiceAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                current as Service
                startActivity(Intent(context, ServiceActivity::class.java).apply {
                    putExtra(Parameters.key, current.service_id)
                })
            }
        }
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            lateinit var companies: Array<Profile>
            lateinit var communities: Array<Profile>
            lateinit var teams: Array<Profile>
            override suspend fun body(block: CoroutineScope) {
                companies =
                    withContext(block.coroutineContext) {
                        InitRequest.profiles(
                            Role.company,
                            Filter.popular,
                            false,
                            9
                        )
                    }
                communities = withContext(block.coroutineContext) {
                    InitRequest.profiles(
                        Role.community,
                        Filter.popular,
                        false,
                        9
                    )
                }
                teams = withContext(block.coroutineContext) {
                    InitRequest.profiles(
                        Role.team,
                        Filter.popular,
                        false,
                        9
                    )
                }
            }

            override fun onSuccess(item: Any?) {
                if (companies.isNotEmpty()) recyclerViewAdapter.list.add(
                    Recommendation(
                        1,
                        "Компании",
                        companies
                    )
                )
                if (communities.isNotEmpty()) recyclerViewAdapter.list.add(
                    Recommendation(
                        2,
                        "Сообщества",
                        communities
                    )
                )
                if (teams.isNotEmpty()) recyclerViewAdapter.list.add(
                    Recommendation(
                        3,
                        "Команды",
                        teams
                    )
                )
                recyclerViewAdapter.updateList()
            }
        }
    }

    override fun init() {
        serviceAdapter.initList(
            Service(
                ServiceActivity.networking, R.drawable.img_networking,
                requireContext().getString(R.string.Networking)
            ), Service(
                ServiceActivity.resumes, R.drawable.img_find_worker,
                requireContext().getString(R.string.Find_worker)
            ), Service(
                ServiceActivity.vacancies,
                R.drawable.img_find_vacancy,
                requireContext().getString(R.string.Find_vacancy)
            ), Service(
                ServiceActivity.articles,
                R.drawable.img_articles,
                requireContext().getString(R.string.News)
            ), Service(
                ServiceActivity.polls,
                R.drawable.img_polls,
                requireContext().getString(R.string.Polls)
            ), Service(
                ServiceActivity.statistic,
                R.drawable.img_profile_statistics,
                requireContext().getString(R.string.ProfileStatistics)
            )
        )
        recyclerView.adapter = recyclerViewAdapter
        serviceRecyclerView.adapter = serviceAdapter
    }

    override fun initVariable() {
        binding = FragmentServiceBinding.inflate(layoutInflater)
        recyclerView = binding.rvRecommendation
        serviceRecyclerView = binding.rvService
    }

    override fun initButtons() {
        binding.tvSearch.setOnClickListener {
            navController.navigate(R.id.action_categoryFragment_to_searchProfileFragment)
        }
    }

}