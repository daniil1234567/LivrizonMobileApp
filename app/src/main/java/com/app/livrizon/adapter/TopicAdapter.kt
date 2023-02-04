package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.impl.Base
import com.app.livrizon.model.topics.Topic
import kotlinx.android.synthetic.main.item_article_layout.view.tv_name
import kotlinx.android.synthetic.main.item_topic_layout.view.*

abstract class TopicAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_topic_layout
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val topic = list[position] as Topic
        with(holder.itemView) {
            tv_name.text = topic.name
            ic_more.visibility = if (topic.topic_id == null) View.VISIBLE
            else View.GONE
            if (topic.choose) holder.itemView.ic_choose.visibility = View.VISIBLE
            else holder.itemView.ic_choose.visibility = View.GONE
        }
    }

    override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
        val topic = list[position] as Topic
        topic.choose = !topic.choose
        notifyItemChanged(position)
    }
}