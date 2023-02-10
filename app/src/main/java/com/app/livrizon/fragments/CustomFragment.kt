package com.app.livrizon.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.app.livrizon.adapter.RecyclerViewAdapterBase
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.model.Tab
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ScrollListener
import com.app.livrizon.request.WebSocketListener
import com.app.livrizon.util.TextListener
import com.app.livrizon.view_model.ViewModel
import com.google.android.material.tabs.TabLayout

abstract class CustomFragment : Fragment() {
    val viewModel: ViewModel by activityViewModels()
    var initRequest: HttpListener? = null
    var webSocketListener: WebSocketListener? = null
    lateinit var textListener: TextListener
    lateinit var scrollListener: ScrollListener
    lateinit var navController: NavController
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: RecyclerViewAdapterBase
    lateinit var tabs: Array<Tab>
    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var launcher: ActivityResultLauncher<Intent>
    var offset = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
        initVariable()
        initAdapter()
        initTabLayout()
        initButtons()
        initListener()
        request()
        init()
        initRequest?.request()
        webSocketListener?.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketListener?.close()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return getBindingRoot()
    }

    abstract fun getBindingRoot(): View

    protected open fun init() {

    }

    protected open fun request() {

    }

    protected abstract fun initVariable()

    protected open fun initAdapter() {

    }

    protected open fun initListener() {

    }

    protected open fun initTabLayout() {

    }

    protected open fun initButtons() {

    }
}