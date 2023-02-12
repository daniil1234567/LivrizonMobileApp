package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.RegistrationRoleAdapter
import com.app.livrizon.databinding.FragmentRegistrationRoleBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.impl.Base
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.RegistrationRole
import com.app.livrizon.security.Role
import kotlinx.android.synthetic.main.item_registration_role_layout.view.*

class RegistrationRoleFragment : CustomFragment() {
    lateinit var binding: FragmentRegistrationRoleBinding
    lateinit var authentication: Authentication
    var list = mutableListOf<RegistrationRole>()
    var role = Role.user
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun init() {
        recyclerViewAdapter.initList(*list.toTypedArray())
    }

    override fun initVariable() {
        binding = FragmentRegistrationRoleBinding.inflate(layoutInflater)
        viewModel.choose.value = 1
        recyclerView = binding.rvCategories
        list.add(
            RegistrationRole(
                1,
                Role.user,
                getString(R.string.User),
                getString(R.string.UserRegistrationDescription)
            )
        )
        list.add(
            RegistrationRole(
                2,
                Role.user,
                getString(R.string.Recruiter),
                getString(R.string.RecruiterRegistrationDescription)
            )
        )
        list.add(
            RegistrationRole(
                3,
                Role.company,
                getString(R.string.Company),
                getString(R.string.CompanyRegistrationDescription)
            )
        )
    }

    override fun initAdapter() {
        recyclerViewAdapter = object : RegistrationRoleAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                viewModel.choose.value = current.id()
            }

            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                current as RegistrationRole
                viewModel.choose.observe(this@RegistrationRoleFragment) {
                    if (current.id() == it) current.choose = !current.choose
                    if (current.choose) {
                        role = current.role
                        holder.itemView.cr_choose.visibility = View.VISIBLE
                        holder.itemView.cr_not_choose.visibility = View.GONE
                    } else {
                        holder.itemView.cr_choose.visibility = View.GONE
                        holder.itemView.cr_not_choose.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}