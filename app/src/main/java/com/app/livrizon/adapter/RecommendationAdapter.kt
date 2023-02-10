package com.app.livrizon.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import com.app.livrizon.R
import com.app.livrizon.fragments.profile.ProfileList
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.Recommendation
import com.app.livrizon.model.Tab
import io.ktor.util.*
import kotlinx.android.synthetic.main.item_recommendation_layout.view.*
import kotlin.math.min

abstract class RecommendationAdapter(context: Context, val fragment: Fragment) :
    RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_recommendation_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val recommendation = list[position] as Recommendation
        holder.itemView.tv_header.text = recommendation.header
        val viewPagerAdapter = ViewPagerAdapter(fragment)
        val size = recommendation.recommendation.size
        for (i in 0 until size step 3) {
            viewPagerAdapter.list.add(
                Tab(
                    i / 3 + 1,
                    ProfileList(
                        recommendation.recommendation.slice(i until min(i + 3, size)).toTypedArray(),
                        this
                    ),
                    null
                )
            )
        }
        viewPagerAdapter.updateList()
        holder.itemView.viewPager2.adapter = viewPagerAdapter
    }
}