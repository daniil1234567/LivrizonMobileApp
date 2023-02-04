package com.app.livrizon.model

import androidx.fragment.app.Fragment
import com.app.livrizon.impl.Base

class Tab(
    val tab_id: Int,
    val fragment: Fragment,
    val title: String?
):Base{
    override fun id(): Int {
        return tab_id
    }
}