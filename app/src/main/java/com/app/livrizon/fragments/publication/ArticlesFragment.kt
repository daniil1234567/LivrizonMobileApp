package com.app.livrizon.fragments.publication

import android.view.View
import com.app.livrizon.adapter.ArticleAdapter
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.publication.Article
import com.app.livrizon.model.publication.Auth
import com.app.livrizon.model.publication.Popular
import com.app.livrizon.model.publication.PreviewPublication
import com.app.livrizon.request.Filter
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.math.min

class ArticlesFragment : CustomFragment() {
    lateinit var binding: FragmentListBinding

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun request() {
        initRequest =
            object : HttpListener(requireContext()) {
                override suspend fun body(block: CoroutineScope): Array<Base> {
                    val list = mutableListOf<Base>()
                    list.addAll(withContext(block.coroutineContext) {
                        InitRequest.articles(
                            Filter.popular,
                            1,
                            Array<Popular>::class.java
                        )
                    })
                    list.addAll(withContext(block.coroutineContext) {
                        InitRequest.articles(
                            Filter.recommendation,
                            30,
                            Array<Article>::class.java
                        )
                    })
                    list.addAll(
                        min(7, list.size),
                        withContext(block.coroutineContext) {
                            InitRequest.articles(
                                Filter.author,
                                5,
                                Array<Auth>::class.java
                            ).toList()
                        })
                    return list.toTypedArray()
                }

                override fun onSuccess(item: Any?) {
                    item as Array<Base>
                    recyclerViewAdapter.initList(*item)
                }

            }
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ArticleAdapter(requireContext()) {

        }
    }


    override fun initVariable() {
        binding = FragmentListBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
    }
}