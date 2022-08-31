package com.sebqv97.myapplication.feature_users.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sebqv97.myapplication.feature_users.domain.model.SearchUsersItemModel

data class SearchUsersItemDto(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
){
    fun toSearchUsersItemModel():SearchUsersItemModel = SearchUsersItemModel(
        id = id,
        avatarUrl = avatarUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        reposUrl = reposUrl,
        starredUrl = starredUrl,
        type = type,
        siteAdmin = siteAdmin,
        subscriptionsUrl = subscriptionsUrl,
        htmlUrl = htmlUrl,
        username = login,
        score = score,
        url = url

    )
}