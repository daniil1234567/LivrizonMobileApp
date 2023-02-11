package com.app.livrizon.activities

import com.app.livrizon.R
import com.app.livrizon.function.createPref
import com.app.livrizon.function.lightStatusBar
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.account_pref


class StartActivity : CustomActivity() {

    override fun getStartDestination(): Int {
        return R.id.splashFragment
    }

    override fun getGraph(): Int {
        return R.navigation.start_graph
    }

    override fun init() {
        super.init()
        //setFullScreen(window)
        lightStatusBar(window)
        account_pref = createPref(this, Parameters.accounts)
    }
}
