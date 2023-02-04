package com.app.livrizon.activities

import com.app.livrizon.R

class SubActivity : CustomActivity() {
    override fun getStartDestination(): Int {
        return R.id.subFragment
    }

    override fun getGraph(): Int {
        return R.navigation.main_graph
    }
}