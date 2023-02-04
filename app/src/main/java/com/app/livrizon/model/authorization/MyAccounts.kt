package com.app.livrizon.model.authorization

import kotlinx.serialization.Serializable

@Serializable
class MyAccounts(
    val accounts:List<Authentication>
)