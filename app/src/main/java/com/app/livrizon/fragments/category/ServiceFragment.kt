package com.app.livrizon.fragments.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.R
import com.app.livrizon.adapter.RecommendationAdapter
import com.app.livrizon.adapter.ServiceAdapter
import com.app.livrizon.databinding.FragmentServiceBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.Recommendation
import com.app.livrizon.model.init.ServiceInit
import com.app.livrizon.model.service.Service
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest

class ServiceFragment : CustomFragment() {
    lateinit var binding: FragmentServiceBinding
    lateinit var serviceAdapter: ServiceAdapter
    lateinit var serviceRecyclerView: RecyclerView
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : RecommendationAdapter(requireContext(), this) {

        }
        serviceAdapter = object : ServiceAdapter(requireContext()) {

        }
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(): ServiceInit {
                return InitRequest.services()
            }

            override fun onSuccess(item: Any?) {
                item as ServiceInit
                if (item.companies.isNotEmpty()) recyclerViewAdapter.list.add(Recommendation(1,"Компании", item.companies))
                if (item.communities.isNotEmpty()) recyclerViewAdapter.list.add(Recommendation(2,"Сообщества",  item.communities))
                if (item.teams.isNotEmpty()) recyclerViewAdapter.list.add(Recommendation(3, "Команды", item.teams))
                recyclerViewAdapter.updateList()
            }
        }
    }

    override fun init() {
        serviceAdapter.initList(
            Service(
                networking, R.drawable.img_networking,
                requireContext().getString(R.string.Networking)
            ), Service(
                resumes, R.drawable.img_find_worker,
                requireContext().getString(R.string.Find_worker)
            ), Service(
                vacancies,
                R.drawable.img_find_vacancy,
                requireContext().getString(R.string.Find_vacancy)
            ), Service(
                articles,
                R.drawable.img_articles,
                requireContext().getString(R.string.News)
            ), Service(
                polls,
                R.drawable.img_polls,
                requireContext().getString(R.string.Polls)
            ), Service(
                statistic,
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

    companion object {
        const val networking = 0
        const val articles = 1
        const val resumes = 2
        const val vacancies = 3
        const val polls = 4
        const val statistic = 5
    }
}