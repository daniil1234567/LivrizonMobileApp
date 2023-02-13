package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.log
import com.app.livrizon.impl.Base
import com.app.livrizon.model.publication.Auth
import com.app.livrizon.model.publication.PreviewPublication
import com.app.livrizon.model.publication.PublicationBase.Companion.article
import com.app.livrizon.model.publication.PublicationBase.Companion.popular
import kotlinx.android.synthetic.main.item_author_layout.view.*

abstract class ArticleAdapter(context: Context) : RecyclerViewAdapterBase(context) {

    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            popular -> R.layout.item_popular_article_layout
            article -> R.layout.item_article_layout
            else -> R.layout.item_author_layout
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val article = list[position] as PreviewPublication
        holder.itemView.tv_title.text = article.body.text
        holder.itemView.tv_name.text = article.from.name
    }

    override fun setBody(
        holder: CustomViewHolder,
        position: Int,
        previous: Base?,
        current: Base,
        next: Base?
    ) {
        if (current is Auth) holder.itemView.tv_header.visibility =
            if (previous !is Auth) View.VISIBLE else View.GONE
    }
}