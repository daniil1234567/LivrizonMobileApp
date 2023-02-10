package com.app.livrizon.fragments.publication

import android.view.View
import com.app.livrizon.adapter.ArticleAdapter
import com.app.livrizon.databinding.FragmentListBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.publication.PreviewPost
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                override suspend fun body(block: CoroutineScope): Array<PreviewPost> {
                    val list = mutableListOf<PreviewPost>()
                    val popular = withContext(block.coroutineContext) { InitRequest.popular() }
                    if (popular != null) list.add(popular)
                    list.addAll(withContext(block.coroutineContext) { InitRequest.articles() })
                    list.addAll(
                        min(6, list.size),
                        withContext(block.coroutineContext) { InitRequest.authors() }.toList()
                    )
                    return list.toTypedArray()
                }

                override fun onSuccess(item: Any?) {
                    item as Array<PreviewPost>
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