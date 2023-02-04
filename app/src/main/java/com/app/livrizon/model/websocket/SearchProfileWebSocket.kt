package com.app.livrizon.model.websocket


data class SearchProfileWebSocket(
    var text: String? = null,
    val city: Int? = null,
    val gens: List<Int> = listOf(1,2,3,4),
    val last: Int? = null,
)