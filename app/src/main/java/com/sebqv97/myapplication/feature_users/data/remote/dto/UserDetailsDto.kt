package com.sebqv97.myapplication.feature_users.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sebqv97.myapplication.feature_users.data.local.entity.UserDetailsEntity

data class UserDetailsDto(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following")
    val following: Int?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("gists_url")
    val gistsUrl: String?,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    @SerializedName("hireable")
    val hireable: Boolean?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("organizations_url")
    val organizationsUrl: String?,
    @SerializedName("public_gists")
    val publicGists: Int?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,
    @SerializedName("starred_url")
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?
){
    fun toUserDetailsEntity():UserDetailsEntity = UserDetailsEntity(
        avatarUrl = avatarUrl,
        bio = bio,
        blog = blog,
        createdAt = createdAt,
        email = email,
        followers = followers,
        following = following,
        followingUrl = followingUrl,
        hireable = hireable,
        id = id,
        username = login,
        name = name,
        reposUrl = reposUrl,
        twitterUsername = twitterUsername,
        location = location,
        publicReposNumber = publicRepos,
        profileUrl = htmlUrl
    )
}