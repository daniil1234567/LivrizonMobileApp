package com.app.livrizon.fragments.profile

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.MoveImpl
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentProfileSearchListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.scroll.ProfileSearchScroll
import com.app.livrizon.request.*
import com.app.livrizon.values.WebsocketsRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchProfileListFragment(val move: MoveImpl) : CustomFragment(), MoveImpl {
    lateinit var binding: FragmentProfileSearchListBinding
    lateinit var visitRecyclerView: RecyclerView
    lateinit var visitAdapter: ProfileAdapter
    var text: String? = null
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initListener() {
        webSocketListener = object : WebSocketListener(requireContext(), WebsocketsRoute.profiles) {

        }
        //.addListener(EventType.response, object : WebSocketChanelListener() {
        //    override fun inputMessage(text: String) {
        //        val search = gson.fromJson(text, Array<Search>::class.java)
        //        if (this@SearchProfileListFragment.text == viewModel.search.value) recyclerViewAdapter.addListToBottom(
        //            *search
        //        )
        //        else {
        //            this@SearchProfileListFragment.text = viewModel.search.value!!
        //            recyclerViewAdapter.setList(*search)
        //        }
        //    }
        //})
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
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                move.moveToWall(current)
            }

            override fun setButton(holder: CustomViewHolder, current: Base) {

            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base) {
                current as ProfileBase
                object : HttpListener(requireContext()) {
                    override suspend fun body(block: CoroutineScope): Response {
                        return ProfileRequest.hideRecent(current.profile_id)
                    }

                    override fun onSuccess(item: Any?) {
                        visitAdapter.removeItem {
                            it.equals() == current.equals()
                        }
                    }
                }.request()
            }
        }
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                move.moveToWall(current)
            }
        }
    }

    override fun initVariable() {
        binding = FragmentProfileSearchListBinding.inflate(layoutInflater)
        recyclerView = binding.rvSearch
        visitRecyclerView = binding.rvVisit
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            lateinit var visits: Array<ProfileBase>
            lateinit var profiles: Array<Profile>
            override suspend fun body(block: CoroutineScope) {
                visits = withContext(block.coroutineContext) {
                    InitRequest.profiles(
                        Selection.visits,
                        null,
                        Filter.recent,
                        true,
                        Sort.def,
                        30,
                    )
                }
                profiles = withContext(block.coroutineContext) {
                    InitRequest.profiles(
                        Selection.search,
                        null,
                        null,
                        false,
                        Sort.def,
                        30,
                    )
                } as Array<Profile>
            }

            override fun onSuccess(item: Any?) {
                visitAdapter.initList(*visits)
                recyclerViewAdapter.initList(*profiles)
            }
        }
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        visitRecyclerView.adapter = visitAdapter
        scrollListener.addScrollListener(recyclerView)
    }
}