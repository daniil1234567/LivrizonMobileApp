package com.app.livrizon.util

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import com.app.livrizon.function.log

abstract class TextListener(private val delay: Long) : TextWatcher {
    var latest: Long = 0
    var last:String? = null
    private fun filterWithDelay(s: String?) {
        latest = System.currentTimeMillis()
        val h = Handler(Looper.getMainLooper())
        h.postDelayed({
            if (System.currentTimeMillis() >= latest - 1 + delay && s != last) {
                last = s!!
                onInputEnd(s)
            }
        }, delay)
    }

    abstract fun onInputEnd(s: String)
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        filterWithDelay(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}