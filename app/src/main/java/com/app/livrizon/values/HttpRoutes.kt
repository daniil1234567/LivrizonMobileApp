package com.app.livrizon.values

import com.app.livrizon.request.Sub

object HttpRoutes {
    const val host = "89.223.68.211"
    const val port = 80
    private const val BASE_URL = "http://$host"
    const val login = "$BASE_URL/login"
    const val confirm = "$BASE_URL/confirm"
    const val registration = "$BASE_URL/registration"
    const val confirm_account = "$BASE_URL/confirm/account"
    const val password = "$BASE_URL/password"
    const val confirm_name = "$BASE_URL/confirm/name"
    const val authentication = "$BASE_URL/authentication"
    const val information = "$BASE_URL/information"
    const val account = "$BASE_URL/account"
    const val init_home = "$BASE_URL/init/home"
    const val post = "$BASE_URL/post"
    const val accounts = "$BASE_URL/accounts"
    const val seen = "$BASE_URL/seen"
    const val interests = "$BASE_URL/interests"
    const val init_messenger = "$BASE_URL/init/messenger"
    const val init_chats = "$BASE_URL/init/chats"
    const val init_append = "$BASE_URL/init/append"
    const val init_posts = "$BASE_URL/init/posts"
    const val init_visits = "$BASE_URL/init/visits"
    const val init_profile_search = "$BASE_URL/init/profile/search"
    const val init_services = "$BASE_URL/init/services"

    const val page = "$BASE_URL/page"
    const val post_like = "$BASE_URL/post_like"
    const val team = "$BASE_URL/team"
    const val topics = "$BASE_URL/topics"
    const val init_profiles = "$BASE_URL/init/profiles"

    fun initProfile(sub: Sub): String {
        return "$BASE_URL/init/profile/$sub"
    }

    fun visit(profile_id: Int):String {
        return "$BASE_URL/visit/$profile_id"
    }
    fun sub(profile_id: Int): String {
        return "$BASE_URL/sub/$profile_id"
    }

    fun wall(profile_id: Int): String {
        return "$BASE_URL/wall/$profile_id"
    }

    fun initSub(sub: Sub, profile_id: Int): String {
        return "$BASE_URL/init/sub/$sub/$profile_id"
    }

    fun information(profile_id: Int): String {
        return "$BASE_URL/information/$profile_id"
    }

    fun previewChat(profile_id: Int): String {
        return "$BASE_URL/preview/chat/$profile_id"
    }

    fun message(profile_id: Int): String {
        return "$BASE_URL/message/$profile_id"
    }

    fun messages(profile_id: Int): String {
        return "$BASE_URL/messages/$profile_id"
    }

    fun initChat(profile_id: Int): String {
        return "$BASE_URL/init/chat/$profile_id"
    }

    fun initMessages(profile_id: Int): String {
        return "$BASE_URL/init/messages/$profile_id"
    }

    fun image(url: String, quality: Int = 3): String {
        return "$BASE_URL/file/$url?quality=$quality"
    }
}