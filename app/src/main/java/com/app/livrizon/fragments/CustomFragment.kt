package com.app.livrizon.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.app.livrizon.adapter.RecyclerViewAdapterBase
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.WebSocketListener
import com.app.livrizon.util.TextListener
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class CustomFragment : Fragment() {
    lateinit var httpListener: HttpListener
    var webSocketListener: WebSocketListener? = null
    lateinit var textListener: TextListener
    lateinit var navController: NavController
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: RecyclerViewAdapterBase
    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
        initVariable()
        initAdapter()
        initTabLayout()
        setButtons()
        initButtons()
        initListener()
        request()
        init()
        CoroutineScope(Dispatchers.IO).launch {
            delay(50)
            transition()
        }
        //webSocketListener?.connect()
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

    protected open fun transition() {

    }

    protected open fun initAdapter() {

    }

    protected open fun initListener() {

    }

    protected open fun initTabLayout() {

    }

    protected open fun setButtons() {

    }

    protected open fun initButtons() {

    }
}