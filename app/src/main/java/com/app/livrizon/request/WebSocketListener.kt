package com.app.livrizon.request

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.app.livrizon.function.createWebSocketClient
import com.app.livrizon.function.log
import com.app.livrizon.function.toast
import com.app.livrizon.model.response.WebSocketResponse
import com.app.livrizon.model.type.event.EventType
import com.app.livrizon.values.HttpRoutes
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token
import com.app.livrizon.values.gson
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

open class WebSocketListener(
    val context: Context,
    private var path: String,
) {
    private val params: MutableList<Pair<String, Any>> = mutableListOf()
    fun addParam(name: String, value: Any?): WebSocketListener {
        if(value!=null) params.add(Pair(name, value))
        return this
    }

    private val listeners = HashMap<EventType, WebSocketChanelListener>()
    var webSocketClient: HttpClient? = null
    private var session: WebSocketSession?=null
    var loading = false
    var init = false
    fun addListener(
        type: EventType,
        listener: WebSocketChanelListener
    ): WebSocketListener {
        listeners[type] = listener
        return this
    }


    fun connect() {
        log("loading websocket $path")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                webSocketClient = createWebSocketClient()
                webSocketClient!!.webSocket(
                    method = HttpMethod.Get,
                    host = HttpRoutes.host,
                    port = HttpRoutes.port,
                    path = path,
                    request = {
                        for (i in 0 until params.size) {
                            parameter(params[i].first, params[i].second)
                        }
                        header(
                            Parameters.auth,
                            token.jwt
                        )
                    }
                ) {
                    loading = false
                    session = this
                    onConnect()
                    if (!init) {
                        onInit()
                    }
                    while (true) {
                        inputMessage(
                            gson.fromJson(
                                (incoming.receive() as? Frame.Text)!!.readText(),
                                WebSocketResponse::class.java
                            )
                        )
                        init = true
                    }
                }
            } catch (e: ClosedReceiveChannelException) {
                onError("$path ${e.message}")
            } catch (e: Exception) {
                onError("$path ${e.message}")
            }

        }

    }

    fun close() {
        webSocketClient?.close()
        log(path, "websockets closed", context)
    }

    suspend fun outputMessage(text: Any? = null) {
        log(gson.toJson(text),"output")
        if (session?.isActive == true) {
            session!!.send(gson.toJson(text))
            loading = false
        }
    }


    open fun onConnect() {
        log(path, "websockets connected", context)
    }

    open fun onInit() {
        log(path, "websockets initialized", context)
    }


    open fun onError(error: Any) {
        log(path, error, context)
        Handler(Looper.getMainLooper()).post {
            toast(context, error)
        }
    }

    private fun inputMessage(response: WebSocketResponse) {
        Handler(Looper.getMainLooper()).post {
            log("input $path",response.body)
            listeners[response.type]?.inputMessage(response.body)
        }
    }

}