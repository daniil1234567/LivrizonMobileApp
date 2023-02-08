package com.app.livrizon.fragments.profile

import android.view.View
import android.widget.TextView
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.adapter.ViewPagerAdapter
import com.app.livrizon.databinding.FragmentConnectionBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.Tab

class ConnectionFragment : CustomFragment() {
    lateinit var binding: FragmentConnectionBinding
    lateinit var possibleAdapter: ViewPagerAdapter

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initButtons() {
        binding.btnMore.setOnClickListener {
            //navController.navigate(R.id.action_connectionFragment_to_profileListWithToolbarFragment,
            //    Bundle().apply {
            //        putInt(Parameters.key, POSSIBLE_ACCOUNTS)
            //        putString(Parameters.title, "Возможные связи")
            //    }
            //)
        }
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {

        }
        possibleAdapter = ViewPagerAdapter(this)
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        viewPager2 = binding.viewPager2
        viewPager2.isSaveEnabled = false
        viewPager2.adapter = possibleAdapter
        //singleRequest(object : HttpListener(requireContext()) {
        //    override suspend fun request(): InitMyConnection {
        //        return InitRequest.myConnection()
        //    }
//
        //    override suspend fun onSuccess(item: Any?) {
        //        val initMyConnection = item as InitMyConnection
        //        binding.tvConnections.text = initMyConnection.connections.toString()
        //        recyclerViewAdapter.updateList(*initMyConnection.profiles)
        //        initMyConnection.possible.let {
        //            if (it != null) {
        //                for (i in it.indices step 3) {
        //                    tabs.add(
        //                        Tab(
        //                            i,
        //                            ProfileBaseFragment(
        //                                it.slice(i..min(i + 2, it.size - 1)).toTypedArray()
        //                            ),
        //                            null
        //                        )
        //                    )
        //                }
        //                possibleAdapter.serList(*tabs.toTypedArray())
        //            }
        //        }
//
        //    }
        //})
    }

    override fun initVariable() {
        binding = FragmentConnectionBinding.inflate(layoutInflater)
        recyclerView = binding.rvProfile
    }

    //override fun onBodyClick(profile: Profile) {
//
    //}
//
    //override fun setBody(view: View, position: Int, item: ProfileObject) {
//
    //}

}