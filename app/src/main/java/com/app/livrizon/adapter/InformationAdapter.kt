package com.app.livrizon.adapter

import android.content.Context
import com.app.livrizon.R
import com.app.livrizon.model.wall.WallInformation

abstract class InformationAdapter(context: Context) : RecyclerViewAdapterBase(context) {
    override fun getLayout(viewType: Int): Int {
        return when (viewType) {
            WallInformation.title -> R.layout.item_title_layout
            WallInformation.description -> R.layout.item_description_layout
            WallInformation.education -> R.layout.item_education_layout
            WallInformation.experience -> R.layout.item_experience_layout
            WallInformation.resume -> R.layout.item_resume_layout
            else -> R.layout.item_contact_layout
        }
    }

}