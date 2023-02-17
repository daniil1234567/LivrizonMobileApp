package com.app.livrizon.fragments.authentication

import android.os.Bundle
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.GroupTopicAdapter
import com.app.livrizon.databinding.FragmentRegistrationTopicsBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.impl.Base
import com.app.livrizon.model.edit.topic.TopicEdit
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.topics.GroupTopics
import com.app.livrizon.model.topics.Topic
import com.app.livrizon.request.*
import com.app.livrizon.security.Role
import com.app.livrizon.values.Parameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationTopicsFragment : CustomFragment() {
    lateinit var binding: FragmentRegistrationTopicsBinding
    lateinit var profileRequest: HttpListener
    lateinit var homeRequest: HttpListener
    lateinit var topicRequest: HttpListener
    var filter: Filter? = null
    var topics = mutableListOf<TopicEdit>()
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : GroupTopicAdapter(requireContext()) {
            override fun onButtonClick(holder: CustomViewHolder, current: Base) {
                if (current.id() != null) {
                    if (topics.find { it.topic_id == current.id() } == null) topics.add(
                        TopicEdit(
                            current.id()!!,
                            true
                        )
                    )
                    else topics.removeIf { it.topic_id == current.id() }
                }
            }
        }

    }


    override fun initVariable() {
        binding = FragmentRegistrationTopicsBinding.inflate(layoutInflater)
        recyclerView = binding.rvGroupInterest
    }

    override fun init() {
        recyclerView = binding.rvGroupInterest
        recyclerView.adapter = recyclerViewAdapter
        CoroutineScope(Dispatchers.Main).launch {
            recyclerViewAdapter.initList(
                GroupTopics(
                    1,
                    requireContext().getString(R.string.News),
                    arrayOf(
                        Topic(1, requireContext().getString(R.string.Politics)),
                        Topic(2, requireContext().getString(R.string.GeneralNews)),
                        Topic(3, requireContext().getString(R.string.Technologies)),
                        Topic(4, requireContext().getString(R.string.Health)),
                        Topic(name = requireContext().getString(R.string.More))
                    )
                ), GroupTopics(
                    2,
                    requireContext().getString(R.string.Business),
                    arrayOf(
                        Topic(5, "Стартапы"),
                        Topic(6, "Инвестиции"),
                        Topic(7, "Компании"),
                        Topic(8, "Работа"),
                        Topic(name = requireContext().getString(R.string.More))
                    )
                ), GroupTopics(
                    3,
                    requireContext().getString(R.string.Art),
                    arrayOf(
                        Topic(9, "Музыка"),
                        Topic(10, "Живопись"),
                        Topic(11, "Танцы"),
                        Topic(12, "Архитектура"),
                        Topic(name = requireContext().getString(R.string.More))
                    )
                ), GroupTopics(
                    4,
                    requireContext().getString(R.string.Sciences),
                    arrayOf(
                        Topic(13, "История"),
                        Topic(14, "Языки"),
                        Topic(15, "Астрономия"),
                        Topic(16, "Психология"),
                        Topic(17, "Криптография"),
                        Topic(name = requireContext().getString(R.string.More))
                    )
                ), GroupTopics(
                    5,
                    requireContext().getString(R.string.Industries),
                    arrayOf(
                        Topic(18, "Строительство"),
                        Topic(19, "Сельское хозяйство"),
                        Topic(20, "It"),
                        Topic(21, "Животные"),
                        Topic(name = requireContext().getString(R.string.More))
                    )
                ), GroupTopics(
                    6,
                    requireContext().getString(R.string.Entertainments),
                    arrayOf(
                        Topic(22, "Спорт"),
                        Topic(23, "Кино"),
                        Topic(24, "Фотография"),
                        Topic(25, "Фильмы"),
                        Topic(name = requireContext().getString(R.string.More))
                    )
                )
            )
        }
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            if (topics.size > 0) topicRequest.request()
            else profileRequest.request()
        }
    }

    override fun request() {
        homeRequest = homeRequest(this)
        profileRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Array<Profile> {
                filter =
                    if (topics.size > 0 && filter == null) Filter.recommendation else Filter.popular
                return InitRequest.profiles(
                    Selection.possible,
                    null,
                    filter,
                    false,
                    Sort.popularity,
                    30,
                    Role.user,
                    Role.company
                ) as Array<Profile>
            }

            override fun onSuccess(item: Any?) {
                item as Array<Profile>
                if (item.size > 0)
                    navController.navigate(R.id.action_registrationTopicsFragment_to_registrationAccountRecommendationFragment,
                        Bundle().apply {
                            putSerializable(Parameters.profile, item)
                        }
                    )
                else if (filter == Filter.recommendation) {
                    filter = Filter.popular
                    profileRequest.request()
                } else homeRequest.request()
            }
        }
        topicRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Response {
                return ProfileRequest.topics(topics.toTypedArray())
            }

            override fun onSuccess(item: Any?) {
                profileRequest.request()
            }
        }
    }

}

