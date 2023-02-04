package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.function.loadAvatar
import com.app.livrizon.model.publication.Article
import com.app.livrizon.model.publication.Author
import com.app.livrizon.model.publication.PublicationBase
import com.app.livrizon.model.publication.body.AuthorBody
import kotlinx.android.synthetic.main.item_article_layout.view.tv_header
import kotlinx.android.synthetic.main.item_article_layout.view.tv_name
import kotlinx.android.synthetic.main.item_article_layout.view.tv_secondary
import kotlinx.android.synthetic.main.item_author_layout.view.*

abstract class ArticleAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) PublicationBase.popular
        else super.getItemViewType(position)
    }

    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            PublicationBase.popular -> R.layout.item_article_popular
            PublicationBase.article -> R.layout.item_article_layout
            else -> R.layout.item_author_layout
        }
    }

}