package com.app.livrizon.activities

import com.app.livrizon.R

class InformationActivity : CustomActivity() {
    override fun getStartDestination(): Int {
        return R.id.informationFragment
    }

    override fun getGraph(): Int {
        return R.navigation.information_graph
    }
}