package com.app.livrizon.model.response

import com.app.livrizon.model.type.event.EventType

class WebSocketResponse(
    val type: EventType,
    val body: String
) : java.io.Serializable