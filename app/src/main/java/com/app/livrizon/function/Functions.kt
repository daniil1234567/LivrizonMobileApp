package com.app.livrizon.function

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.livrizon.R
import com.app.livrizon.activities.MainActivity
import com.app.livrizon.activities.SecondaryActivity
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.model.publication.Post
import com.app.livrizon.model.response.WallResponse
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.model.wall.*
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.InitRequest
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.security.Role
import com.app.livrizon.security.Status
import com.app.livrizon.sql.DbHelper
import com.app.livrizon.sql.DbItem
import com.app.livrizon.values.HttpRoutes
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.connection
import com.app.livrizon.values.gson
import com.squareup.picasso.Picasso
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*

fun setFullScreen(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

fun View.setMargins(
    context: Context,
    left: Float = 0f,
    top: Float = 0f,
    right: Float = 0f,
    bottom: Float = 0f
) {
    val p = this.layoutParams as MarginLayoutParams
    p.setMargins(
        left.toDp(context).toInt(),
        top.toDp(context).toInt(),
        right.toDp(context).toInt(),
        bottom.toDp(context).toInt()
    )
    this.requestLayout()
}

fun Float.toDp(context: Context): Float {
    return (this * context.resources.displayMetrics.density + 0.5f)
}

fun getMessageDescription(type: PublicationType, text: String?, name: String?): String? {
    return when (type) {
        PublicationType.created -> "$name created a chat"
        PublicationType.append -> "$name was added to the chat"
        PublicationType.join -> "$name joined the chat"
        PublicationType.exclude -> "$name was excluded from the chat"
        PublicationType.leave -> "$name left the chat"
        PublicationType.pinned -> "$name fixed the message"
        PublicationType.clear -> "you have cleared the history"
        else -> text
    }
}

fun lightButton(
    context: Context,
    button: TextView,
    text: String = context.getString(R.string.Unsubscribe)
) {
    button.setBackgroundResource(R.drawable.button_light_r8_without_s)
    button.setTextColor(context.resources.getColor(R.color.middle, null))
    button.text = text
}

fun lightStatusBar(window: Window, isLight: Boolean = true) {
    val wic = WindowInsetsControllerCompat(window, window.decorView)
    wic.isAppearanceLightStatusBars = isLight
}


fun loadAvatar(
    name: String?,
    textView: TextView?,
    imageView: ImageView,
    url: String?,
    quality: Int = 5,
) {
    if (url != null) {
        imageView.visibility = View.VISIBLE
        textView?.visibility = View.GONE
        loadImage(imageView, url, quality)
    } else {
        imageView.visibility = View.GONE
        textView?.visibility = View.VISIBLE
        if (name != null) textView!!.text = name[0].uppercaseChar().toString()
    }
}

fun loadImage(imageView: ImageView, path: String, quality: Int = 5) {
    Picasso.get().load(HttpRoutes.image(path, quality)).into(imageView);
    //Glide.with(context)
    //    .load(HttpRoutes.image(path, quality))
    //    .into(imageView)
}

fun <T> List<T>.last(): T? {
    if (isEmpty())
        return null
    return this[lastIndex]
}

fun <T> List<T>.first(): T? {
    if (isEmpty())
        return null
    return this[0]
}
fun wallRequest(fragment: CustomFragment,profile_id: Int):HttpListener{
    return object : HttpListener(fragment.requireContext()) {
        override suspend fun body(): WallResponse {
            return ProfileRequest.wall(profile_id)
        }

        override fun onSuccess(item: Any?) {
            item as WallResponse
            val bundle = Bundle().apply {
                putSerializable(
                    Parameters.posts, gson.fromJson(
                        item.body,
                        if (item.status!= Status.active)
                            DeleteWall::class.java
                        else if (item.role == Role.user) UserWall::class.java
                        else if (item.role == Role.company) CompanyWall::class.java
                        else if (item.role == Role.community) CommunityWall::class.java
                        else TeamWall::class.java
                    )
                )
            }
            fragment.navController.popBackStack()
            fragment.navController.navigate(
                if (item.status!= Status.active) R.id.deleteWallFragment
                else if (!item.available) R.id.restrictWallFragment
                else R.id.wallFragment, bundle
            )

        }
    }
}
fun homeRequest(fragment: CustomFragment): HttpListener {
    return object : HttpListener(fragment.requireContext()) {
        override suspend fun body(): Array<Post> {
            return InitRequest.home()
        }

        override fun onSuccess(item: Any?) {
            item as Array<Post>
            context.startActivity(Intent(context, MainActivity::class.java).apply {
                putExtra(Parameters.posts, item)
            })
            fragment.requireActivity().finish()
        }
    }
}

fun initSecondaryActivity(context: Context, initIntent: (intent: Intent) -> Unit) {
    context.startActivity(
        Intent(context, SecondaryActivity::class.java).apply {
            initIntent(this)
        }
    )
}

fun Int.minuteToMillisecond(): Int {
    return this * 60 * 1000
}


fun FragmentActivity.replace(containerViewId: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction()
        .replace(containerViewId, fragment)
        .commit()
}

fun log(vararg items: Any?) {
    var str = ""
    for (element in items) {
        str += gson.toJson(element.toString())
        str += " "
    }
    Log.d("MyLog", str)

}

fun toast(context: Context, text: Any?) {
    Toast.makeText(
        context, gson.toJson(text),
        Toast.LENGTH_LONG
    ).show()
}


operator fun Int.plus(item: Boolean): Int {
    if (item) this.plus(1)
    return this
}

fun Long.toDate(pattern: String = "dd.MM.yyyy HH.mm"): String {
    return DateFormat.format(pattern, this).toString()
}

fun createHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}

fun createWebSocketClient(): HttpClient {
    return HttpClient(CIO) {
        install(WebSockets)
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}

fun connection(context: Context): SQLiteDatabase {
    return DbHelper(context).writableDatabase
}

fun query(
    table: String,
    columns: Array<String>? = null,
    selection: Array<String>? = null,
    selectionArgs: Array<String>? = null,
    groupBy: String? = null,
    having: String? = null,
    orderBy: String? = null,
    limit: String? = null
): Cursor {
    return connection.query(
        table,
        columns,
        DbItem.Statement.Where.findBy(selection),
        selectionArgs,
        groupBy,
        having,
        orderBy,
        limit
    )
}

fun createPref(context: Context, name: String): SharedPreferences {
    return context.getSharedPreferences(
        name,
        Context.MODE_PRIVATE
    )
}

fun SharedPreferences.putString(param: String, value: String) {
    this.edit().putString(param, value).apply()
}

fun SharedPreferences.clearValue(value: String) {
    this.edit().remove(value).apply()
}

fun <T> MutableList<T>.findPosition(predicate: (T) -> Boolean): Int? {
    for (i in 0 until this.size) {
        if (predicate(this[i])) return i
    }
    return null
}

