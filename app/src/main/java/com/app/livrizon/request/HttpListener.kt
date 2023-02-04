package com.app.livrizon.request

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.app.livrizon.function.log
import com.app.livrizon.function.toast
import com.app.livrizon.model.response.Response
import com.app.livrizon.values.gson
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class HttpListener(val context: Context) {
    var action = 0
    protected abstract suspend fun body(): Any?
    protected open fun onLoading() {
        log("loading http")
    }

    protected open fun onSuccess(item: Any?) {
        log("success")
    }

    protected open fun onError(error: Any?) {
        log(context)
        log(error)
        toast(context, error)
    }

    fun request() {
        onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = body()
                Handler(Looper.getMainLooper()).post {
                    onSuccess(result)
                }

            } catch (e: ClientRequestException) {
                val response=e.response.readText()
                Handler(Looper.getMainLooper()).post {
                    onError(gson.fromJson(response, Response::class.java).response)
                }
            } catch (e: Exception) {
                Handler(Looper.getMainLooper()).post {
                    onError(e)
                }
            }
        }
    }
}