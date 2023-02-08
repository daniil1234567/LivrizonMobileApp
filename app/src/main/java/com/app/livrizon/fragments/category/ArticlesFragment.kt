package com.app.livrizon.fragments.category

import android.view.View
import com.app.livrizon.adapter.ArticleAdapter
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.init.InitNews
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest

class ArticlesFragment : CustomFragment() {
    lateinit var binding: FragmentListBinding
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ArticleAdapter(requireContext()) {

        }
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(): InitNews {
                return InitRequest.news()
            }

            override fun onSuccess(item: Any?) {
                val initNews = item as InitNews
                val articles: MutableList<Base> = initNews.articles.toMutableList()
                if (articles.size > 5) articles.addAll(5, initNews.authors.toList())
                else articles.addAll(initNews.authors.toList())
                recyclerViewAdapter.initList(*articles.toTypedArray());
            }
        }
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun initVariable() {
        binding = FragmentListBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
    }


}