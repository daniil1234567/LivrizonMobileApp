package com.app.livrizon.request

abstract class WebSocketChanelListener {
    abstract fun inputMessage(text: String)
}
