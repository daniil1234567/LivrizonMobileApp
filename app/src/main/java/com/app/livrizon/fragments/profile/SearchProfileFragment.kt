package com.app.livrizon.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.RecyclerViewAdapterImpl
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.databinding.FragmentProfileSearchBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.fragments.list.ProfileListFragment
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.Tab
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.Search
import com.app.livrizon.model.response.Response
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.util.TextListener
import com.app.livrizon.values.Parameters
import com.app.livrizon.view_model.ViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_profile_layout.view.*

class SearchProfileFragment : CustomFragment(), RecyclerViewAdapterImpl {
    val viewModel: ViewModel by activityViewModels()
    lateinit var binding: FragmentProfileSearchBinding
    lateinit var tabs: Array<Tab>


    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initListener() {
        textListener = object : TextListener(500) {
            override fun onInputEnd(s: String) {
                viewModel.search.value = s
            }

        }

    }

    override fun initAdapter() {
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    override fun initVariable() {
        binding = FragmentProfileSearchBinding.inflate(layoutInflater)
        viewPager2 = binding.viewPager2
        tabLayout = binding.tabProfiles
        tabs = arrayOf(
            Tab(1, SearchProfileListFragment(this), getString(R.string.All)),
            Tab(2, ProfileListFragment(null, null, this), getString(R.string.Peoples)),
            Tab(3, ProfileListFragment(null, null, this), getString(R.string.Сompanies)),
            Tab(4, ProfileListFragment(null, null, this), getString(R.string.Communities)),
            Tab(5, ProfileListFragment(null, null, this), getString(R.string.Teams))
        )
    }

    override fun init() {
        binding.edSearch.addTextChangedListener(textListener)
        viewPager2.isSaveEnabled = false
        viewPager2.adapter = viewPagerAdapter
        viewPagerAdapter.serList(*tabs)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewPagerAdapter.list[position].title
        }.attach()
        viewPagerAdapter.updateList()
    }

    override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
        current as ProfileBase
        navController.navigate(R.id.action_searchProfileFragment_to_pageShimmer, Bundle().apply {
            putInt(Parameters.profile_id, current.profile_id)
        })
    }

    override fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int) {
        current as Search
        val my_sub = current.my_sub
        object : HttpListener(requireContext()) {
            override suspend fun body(): Response {
                return ProfileRequest.sub(current.profile_id)
            }

            override fun onSuccess(item: Any?) {
                current.my_sub = !my_sub
                with(holder.itemView) {
                    if (current.my_sub) {
                        tv_button.text = "Отписаться"
                        tv_button.setTextColor(resources.getColor(R.color.white))
                        btn_action.setBackgroundResource(R.drawable.button_black_r8_without_s)
                    } else {
                        tv_button.text = "Подписаться"
                        tv_button.setTextColor(resources.getColor(R.color.grey))
                        holder.itemView.btn_action.setBackgroundResource(R.drawable.button_light_r8_without_s)
                    }
                }
            }
        }.request()
    }

    override fun setBody(
        holder: CustomViewHolder,
        position: Int,
        previous: Base?,
        current: Base,
        next: Base?
    ) {
        current as Search
        with(holder.itemView) {
            if (current.my_sub) {
                tv_button.text = "Отписаться"
                tv_button.setTextColor(resources.getColor(R.color.white))
                btn_action.setBackgroundResource(R.drawable.button_black_r8_without_s)
            } else {
                tv_button.text = "Подписаться"
                tv_button.setTextColor(resources.getColor(R.color.black))
                holder.itemView.btn_action.setBackgroundResource(R.drawable.button_light_r8_without_s)
            }
        }
    }

}


