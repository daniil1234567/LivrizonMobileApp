package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.adapter.CategoriesAdapter
import com.app.livrizon.databinding.ActivityMainBinding
import com.app.livrizon.databinding.FragmentCategoriesLayoutBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.profile.Account
import com.app.livrizon.request.HttpListener

class CategoriesFragment : CustomFragment () {
    lateinit var binding: FragmentCategoriesLayoutBinding
    lateinit var authenticationRequest: HttpListener
    lateinit var authentication: Authentication
    lateinit var homeRequest: HttpListener
    lateinit var accounts: Array<Account>

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentCategoriesLayoutBinding.inflate(layoutInflater)
        recyclerView = binding.rvCategories

    }

    override fun initAdapter() {
        recyclerViewAdapter = object : CategoriesAdapter(requireContext()) {
            
        }
    }
}