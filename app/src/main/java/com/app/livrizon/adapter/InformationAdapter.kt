package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.wall.InformationImpl

abstract class InformationAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            InformationImpl.title -> R.layout.item_title_layout
            InformationImpl.description -> R.layout.item_description_layout
            InformationImpl.education -> R.layout.item_education_layout
            InformationImpl.experience -> R.layout.item_experience_layout
            InformationImpl.resume -> R.layout.item_resume_layout
            else -> R.layout.item_contact_layout
        }
    }

}