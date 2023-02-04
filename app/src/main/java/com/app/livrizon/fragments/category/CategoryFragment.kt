package com.app.livrizon.fragments.category

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.activities.SecondaryActivity
import com.app.livrizon.adapter.ServiceAdapter
import com.app.livrizon.databinding.FragmentCategoryBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.initSecondaryActivity
import com.app.livrizon.model.service.Service
import com.app.livrizon.values.Parameters

class CategoryFragment : CustomFragment() {
    lateinit var binding: FragmentCategoryBinding
    lateinit var services: Array<Service>

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ServiceAdapter(requireContext()) {
            override fun onClick(key: Int) {
                initSecondaryActivity(context) {
                    it.putExtra(Parameters.key, key)
                }
            }
        }
    }
    override fun init() {
        recyclerViewAdapter.initList(*services)
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun initVariable() {
        services = arrayOf(
            Service(
                SecondaryActivity.NETWORKING, R.drawable.img_networking,
                requireContext().getString(R.string.Networking)
            ),
            Service(
                SecondaryActivity.NETWORKING, R.drawable.img_find_worker,
                requireContext().getString(R.string.Find_worker)
            ),
            Service(
                SecondaryActivity.NETWORKING,
                R.drawable.img_find_vacancy,
                requireContext().getString(R.string.Find_vacancy)
            ),
            Service(
                SecondaryActivity.ARTICLES,
                R.drawable.img_articles,
                requireContext().getString(R.string.News)
            ),
            Service(
                SecondaryActivity.NETWORKING,
                R.drawable.img_polls,
                requireContext().getString(R.string.Polls)
            ),
            Service(
                SecondaryActivity.NETWORKING,
                R.drawable.img_profile_statistics,
                requireContext().getString(R.string.ProfileStatistics)
            )
        )
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        recyclerView = binding.rvService
    }

    override fun initButtons() {
        binding.tvSearch.setOnClickListener {
            navController.navigate(R.id.action_categoryFragment_to_searchProfileFragment)
        }
    }
}