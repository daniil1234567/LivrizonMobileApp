package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.impl.Base
import com.app.livrizon.model.topics.GroupTopics
import kotlinx.android.synthetic.main.item_article_layout.view.tv_header
import kotlinx.android.synthetic.main.item_topic_group_layout.view.*

abstract class GroupTopicAdapter(context: Context) : RecyclerViewAdapterBase(context) {

    override fun getLayout(viewType: Int): Int {
        return R.layout.item_topic_group_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val groupInterest = list[position] as GroupTopics
        with(holder.itemView) {
            tv_header.text = groupInterest.title
            val recyclerView = rvInterest
            val adapter = object : TopicAdapter(context) {
                override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                    super.onBodyShortClick(holder, current, position)
                    this@GroupTopicAdapter.onButtonClick(holder, current, position)
                }
            }
            adapter.initList(*groupInterest.topics)
            recyclerView.adapter = adapter
        }
    }

}