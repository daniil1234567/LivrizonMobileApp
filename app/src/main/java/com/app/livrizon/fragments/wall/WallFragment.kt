package com.app.livrizon.fragments.wall

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.R
import com.app.livrizon.activities.InformationActivity
import com.app.livrizon.activities.SubActivity
import com.app.livrizon.adapter.*
import com.app.livrizon.databinding.FragmentWallBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.fragments.publication.PostListFragment
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.function.loadImage
import com.app.livrizon.impl.Base
import com.app.livrizon.model.Tab
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.Post
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.wall.Wall
import com.app.livrizon.model.wall.body.WallBody
import com.app.livrizon.model.wall.detail.Button
import com.app.livrizon.model.wall.detail.Detail
import com.app.livrizon.model.wall.detail.Statistic
import com.app.livrizon.model.wall.statistic.WallStatistic
import com.app.livrizon.request.*
import com.app.livrizon.security.Role
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_profile_base_layout.view.*
import kotlinx.coroutines.CoroutineScope

class WallFragment : CustomFragment() {
    lateinit var binding: FragmentWallBinding
    lateinit var buttonAdapter: ButtonAdapter
    lateinit var statisticAdapter: StatisticAdapter
    lateinit var detailAdapter: DetailAdapter
    lateinit var statisticRecyclerView: RecyclerView
    lateinit var detailRecyclerView: RecyclerView
    lateinit var buttonRecyclerView: RecyclerView
    lateinit var wall: Wall
    lateinit var list: Array<Base>
    var mutual: Array<ProfileBase>? = null
    lateinit var subscribeRequest: HttpListener
    lateinit var wallRequest: HttpListener
    private var myPage = false
    private var btnAction = 0

    companion object {
        const val subscriptions = 1
        const val connections = 2
        const val followers = 3
        const val vacancies = 4
        const val recruiters = 5
        const val publications = 6
        const val articles = 7
        const val admins = 8
    }

    override fun initListener() {
        subscribeRequest = object : HttpListener(requireContext()) {
            var subscribe = wall.relation.my_sub
            override suspend fun body(block: CoroutineScope): Response {
                return ProfileRequest.sub(wall.profile.profile_id)
            }

            override fun onSuccess(item: Any?) {
                subscribe = !subscribe
                wall.relation.my_sub = subscribe
            }
        }
    }

    override fun getBindingRoot(): View {
        return binding.root
    }

    private fun initStatistic(statistic: WallStatistic) {
        with(statistic) {
            if (followers != null && (followers >= 0 || myPage)) statisticAdapter.list.add(
                Statistic(
                    WallFragment.followers,
                    followers,
                    "????????????????????",
                    Selection.followers
                )
            )
            if (subscriptions != null && (subscriptions > 0 || myPage)) statisticAdapter.list.add(
                Statistic(
                    WallFragment.subscriptions,
                    subscriptions,
                    "????????????????",
                    Selection.subscriptions
                )
            )
            if (connections != null && (connections > 0 || myPage)) statisticAdapter.list.add(
                Statistic(
                    WallFragment.connections,
                    connections,
                    "??????????",
                    Selection.connections
                )
            )
            if (publications > 0) statisticAdapter.list.add(
                Statistic(
                    WallFragment.publications,
                    publications,
                    "????????????????????",
                )
            )
            if (articles > 0) statisticAdapter.list.add(
                Statistic(articles, this.articles, "????????????")
            )
            if (admins != null && admins > 0) statisticAdapter.list.add(
                Statistic(
                    admins,
                    admins,
                    "????????????????????????????",
                    Selection.followers,
                    Filter.privileged
                )
            )
            if (recruiters > 0) statisticAdapter.list.add(
                Statistic(
                    WallFragment.recruiters,
                    recruiters,
                    "??????????????????",
                    Selection.followers
                )
            )
            if (vacancies > 0) statisticAdapter.list.add(
                Statistic(
                    WallFragment.vacancies,
                    vacancies,
                    "????????????????"
                )
            )
        }
    }

    override fun initButtons() {
        with(wall.relation) {
            with(binding.btnAction) {
                this@WallFragment.btnAction = if (myPage) {
                    text = "??????????????????????????"
                    1
                } else if (!my_sub) {
                    text = "??????????????????????"
                    if (write || chat) buttonAdapter.list.add(
                        Button(
                            Button.chat,
                            R.drawable.ic_chat
                        )
                    )
                    2
                } else if (write || chat) {
                    text = "??????????????????"
                    buttonAdapter.list.add(Button(Button.subscribe, R.drawable.ic_subscribe))
                    4
                } else {
                    text = "????????????????????"
                    3
                }
            }
        }
        binding.btnAction.setOnClickListener {
            when (btnAction) {
                2, 3 -> subscribeRequest.request()
            }
        }
    }


    private fun initBody(body: WallBody) {
        with(body) {
            if (title != null) {
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvTitle.text = title
            } else binding.tvTitle.visibility = View.GONE
            detailAdapter.list.add(Detail(Detail.more, R.drawable.ic_info, "??????????????????"))
            if (contact != null) detailAdapter.list.add(
                Detail(
                    Detail.contact,
                    R.drawable.ic_link,
                    contact.contact,
                )
            )
            if (city != null) detailAdapter.list.add(
                Detail(
                    Detail.city,
                    R.drawable.ic_place,
                    city
                )
            )

        }
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        with(wall) {
            if (wallpaper != null) loadImage(binding.imgWallpaper, wallpaper, 2)
            with(profile) {
                binding.tvName.text = name
                loadAvatar(name, binding.tvImage, binding.imgAvatar, avatar, 4)
                if (confirm) binding.imgConfirm.visibility = View.VISIBLE
                else binding.imgConfirm.visibility = View.GONE
            }
            if (statistic != null) {
                binding.rvStatistic.visibility = View.VISIBLE
                initStatistic(statistic)
            } else binding.rvStatistic.visibility = View.GONE
            initBody(body)
            if (profile.role != Role.team) {
                if (list.isNotEmpty()) {
                    viewPagerAdapter.list.add(
                        Tab(
                            1,
                            PostListFragment(list as Array<Post>),
                            "????????????????????"
                        )
                    )
                }
            }
            if (statistic.articles>0)
                viewPagerAdapter.list.add(
                    Tab(
                        2,
                        PostListFragment(list as Array<Post>),
                        "????????????"
                    )
                )
            if (mutual != null && mutual!!.isNotEmpty()) {
                recyclerViewAdapter.initList(*mutual!!)
                binding.containerMutual.visibility = View.VISIBLE
            } else binding.containerMutual.visibility = View.GONE

        }
        statisticRecyclerView.adapter = statisticAdapter
        statisticAdapter.updateList()
        detailRecyclerView.adapter = detailAdapter
        detailAdapter.updateList()
        buttonRecyclerView.adapter = buttonAdapter
        buttonAdapter.updateList()
        viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = viewPagerAdapter.list[position].title
        }.attach()
        viewPagerAdapter.updateList()
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun setButton(holder: CustomViewHolder, current: Base) {
                holder.itemView.crd_action.visibility = View.GONE
            }
        }
        statisticAdapter = object : StatisticAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                val statistic = list[position] as Statistic
                if (statistic.selection != null)
                    context.startActivity(
                        Intent(context, SubActivity::class.java).apply {
                            putExtra(Parameters.selection, statistic.selection)
                            putExtra(Parameters.title, statistic.statistic)
                            putExtra(Parameters.filter, statistic.filter)
                            putExtra(Parameters.profile_id, wall.profile.profile_id)
                        }
                    )
            }
        }
        buttonAdapter = object : ButtonAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {

            }
        }
        detailAdapter = object : DetailAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                val detail = list[position]
                when (detail.id()) {
                    Detail.more -> context.startActivity(
                        Intent(context, InformationActivity::class.java).apply {
                            putExtra(Parameters.profile_id, wall.profile.profile_id)
                        }
                    )
                }
            }
        }
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    override fun initVariable() {
        binding = FragmentWallBinding.inflate(layoutInflater)
        wall = requireArguments().getSerializable(Parameters.wall) as Wall
        list = requireArguments().getSerializable(Parameters.list) as Array<Base>
        mutual = requireArguments().getSerializable(Parameters.mutual) as Array<ProfileBase>?
        myPage = wall.profile.profile_id == (token as AccessToken).id
        recyclerView = binding.rvMutual
        statisticRecyclerView = binding.rvStatistic
        detailRecyclerView = binding.rvDetail
        buttonRecyclerView = binding.rvButton
        viewPager2 = binding.viewPager2
        tabLayout = binding.tabPublications
    }
}