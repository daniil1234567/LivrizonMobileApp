package com.app.livrizon.request

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.adapter.RecyclerViewAdapterBase
import com.app.livrizon.function.log
import com.app.livrizon.function.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.launch

abstract class ScrollListener(
    val context: Context,
    val offset: Int = 15
) {
    var last: Int? = null
    open suspend fun onView(position: Int) {

    }


    open fun onError(error: Any?) {
        log(context)
        log(error)
        Handler(Looper.getMainLooper()).post {
            toast(context, error)
        }
    }

    open suspend fun onScrolled(speed: Int, offset: Int, unique: Boolean) {

    }

    open suspend fun onScrollToEnd(speed: Int) {

    }

    open fun onScrollStateChanged(newState: Int) {

    }

    fun addScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                onScrollStateChanged(newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val adapter = recyclerView.adapter!! as RecyclerViewAdapterBase
                val fPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val lPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val size = adapter.itemCount
                val speed = if (dx == 0) dy else dx
                if (lPosition > -1) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            for (i in fPosition..lPosition) {
                                onView(i)
                            }
                            if (speed != 0) {
                                val nLast = if (speed < 0) adapter.list.first().id()
                                else adapter.list.last().id()
                                val currentOffset = if (speed < 0) fPosition else size - lPosition
                                val unique = nLast != last
                                if (currentOffset < offset) {
                                    last = nLast
                                    if (unique) onScrollToEnd(speed)
                                }
                                onScrolled(
                                    speed,
                                    currentOffset,
                                    unique
                                )
                            }
                        } catch (e: ClosedReceiveChannelException) {
                            onError(e.toString())
                        } catch (e: Exception) {
                            onError(e.toString())
                        }
                    }
                }
            }

        })
    }
}