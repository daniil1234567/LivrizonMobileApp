package com.app.livrizon.fragments.profile

import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.adapter.RecyclerViewAdapterImpl
import com.app.livrizon.databinding.FragmentProfileSearchListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.init.InitProfileSearch
import com.app.livrizon.model.profile.Search
import com.app.livrizon.model.profile.Visit
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.scroll.ProfileSearchScroll
import com.app.livrizon.model.type.event.EventType
import com.app.livrizon.request.*
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.Selection
import com.app.livrizon.values.WebsocketsRoute
import com.app.livrizon.values.gson
import com.app.livrizon.view_model.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchProfileListFragment(val recyclerViewAdapterImpl: RecyclerViewAdapterImpl) :
    CustomFragment() {
    val viewModel: ViewModel by activityViewModels()
    lateinit var binding: FragmentProfileSearchListBinding
    lateinit var visitRecyclerView: RecyclerView
    lateinit var visitAdapter: ProfileAdapter
    var text: String? = null
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initListener() {
        webSocketListener = object : WebSocketListener(requireContext(), WebsocketsRoute.profiles) {

        }.addParam(Parameters.selection, Selection.search)
            .addListener(EventType.response, object : WebSocketChanelListener() {
                override fun inputMessage(text: String) {
                    val search = gson.fromJson(text, Array<Search>::class.java)
                    if (this@SearchProfileListFragment.text == viewModel.search.value) recyclerViewAdapter.addListToBottom(
                        *search
                    )
                    else {
                        this@SearchProfileListFragment.text = viewModel.search.value!!
                        recyclerViewAdapter.setList(*search)
                    }
                }
            })
        viewModel.search.observe(this) {
            if (it != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    val scroll = ProfileSearchScroll(it, 1, null)
                    webSocketListener!!.outputMessage(scroll)
                }
            }
        }
        scrollListener = object : ScrollListener(requireContext(), 8) {
            override suspend fun onScrollToEnd(speed: Int) {
                log(speed)
            }
        }
    }

    override fun initAdapter() {
        visitAdapter = object : ProfileAdapter(requireContext()) {
            override fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int) {
                current as Visit
                object : HttpListener(requireContext()) {
                    override suspend fun body(): Response {
                        return ProfileRequest.hideRecent(current.profile_id)
                    }

                    override fun onSuccess(item: Any?) {
                        visitAdapter.removeItem {
                            it.equals() == current.equals()
                        }
                    }
                }.request()
            }

            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapterImpl.onBodyShortClick(holder, current, position)
            }
        }
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                recyclerViewAdapterImpl.setBody(holder, position, previous, current, next)
            }

            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapterImpl.onBodyShortClick(holder, current, position)
            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int) {
                recyclerViewAdapterImpl.onButtonClick(holder, current, position)
            }
        }
    }

    override fun initVariable() {
        binding = FragmentProfileSearchListBinding.inflate(layoutInflater)
        recyclerView = binding.rvSearch
        visitRecyclerView = binding.rvVisit
    }

    override fun request() {
        httpListener = object : HttpListener(requireContext()) {
            override suspend fun body(): InitProfileSearch {
                return InitRequest.profileSearch()
            }

            override fun onSuccess(item: Any?) {
                item as InitProfileSearch
                visitAdapter.initList(*item.visits)
                recyclerViewAdapter.initList(*item.profiles)
                webSocketListener!!.connect()
            }
        }
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        visitRecyclerView.adapter = visitAdapter
        scrollListener.addScrollListener(recyclerView)
        httpListener.request()
    }
}