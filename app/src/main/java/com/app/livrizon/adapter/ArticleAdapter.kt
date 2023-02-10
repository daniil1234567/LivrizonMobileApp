package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.publication.Article
import com.app.livrizon.model.publication.PublicationBase.Companion.article
import com.app.livrizon.model.publication.PublicationBase.Companion.popular
import kotlinx.android.synthetic.main.item_author_layout.view.*

abstract class ArticleAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getItemViewType(position: Int): Int {
        val current = list[position]
        return if (position == 0 && current.layout() == article) popular
        else current.layout()
    }

    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            popular -> R.layout.item_popular_article_layout
            article -> R.layout.item_article_layout
            else -> R.layout.item_author_layout
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position != 0) {
            if (position == 1) holder.itemView.tv_header.visibility = View.VISIBLE
            else holder.itemView.tv_header.visibility = View.GONE
        }
        val article = list[position] as Article
        holder.itemView.tv_title.text = article.body.title
        holder.itemView.tv_name.text = article.from.name
    }
}