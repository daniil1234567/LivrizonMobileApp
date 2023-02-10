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
import com.app.livrizon.model.init.ServiceInit
import com.app.livrizon.model.service.Service
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import com.app.livrizon.values.Parameters
import kotlinx.coroutines.CoroutineScope

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
            override suspend fun body(block: CoroutineScope): ServiceInit {
                return InitRequest.services()
            }

            override fun onSuccess(item: Any?) {
                item as ServiceInit
                if (item.companies.isNotEmpty()) recyclerViewAdapter.list.add(
                    Recommendation(
                        1,
                        "Компании",
                        item.companies
                    )
                )
                if (item.communities.isNotEmpty()) recyclerViewAdapter.list.add(
                    Recommendation(
                        2,
                        "Сообщества",
                        item.communities
                    )
                )
                if (item.teams.isNotEmpty()) recyclerViewAdapter.list.add(
                    Recommendation(
                        3,
                        "Команды",
                        item.teams
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