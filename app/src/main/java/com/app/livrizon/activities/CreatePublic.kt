package com.app.livrizon.activities

import com.app.livrizon.R

class CreatePublic : CustomActivity() {
    override fun getStartDestination(): Int {
        return R.id.createPublicFragment
    }

    override fun getGraph(): Int {
        return R.navigation.create_public_graph
    }

}