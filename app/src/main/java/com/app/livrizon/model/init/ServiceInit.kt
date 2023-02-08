package com.app.livrizon.model.init

import com.app.livrizon.model.profile.Profile

class ServiceInit(
    val companies: Array<Profile>,
    val communities: Array<Profile>,
    val teams: Array<Profile>,
) : java.io.Serializable