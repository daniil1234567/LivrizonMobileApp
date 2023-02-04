package com.app.livrizon.model.init

import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.Search
import com.app.livrizon.model.profile.Visit

class InitProfileSearch(
    val visits: Array<Visit>,
    val profiles: Array<Search>
) : java.io.Serializable