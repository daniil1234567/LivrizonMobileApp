package com.app.livrizon.adapter

import android.content.Context
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.model.authorization.RegistrationRole
import kotlinx.android.synthetic.main.item_registration_role_layout.view.*

abstract class RegistrationRoleAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return R.layout.item_registration_role_layout
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val registrationRole = list[position] as RegistrationRole
        with(holder.itemView) {
            tv_title.text = registrationRole.title
            tv_description.text = registrationRole.description
            if (registrationRole.choose) {
                cr_choose.visibility = View.VISIBLE
                cr_not_choose.visibility = View.GONE
            } else {
                cr_choose.visibility = View.GONE
                cr_not_choose.visibility = View.VISIBLE
            }
        }

        super.onBindViewHolder(holder, position)
    }
}