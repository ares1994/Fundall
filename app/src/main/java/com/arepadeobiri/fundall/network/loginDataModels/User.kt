package com.arepadeobiri.fundall.network.loginDataModels


import com.google.gson.annotations.SerializedName


data class User(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("monthly_target")
	val monthlyTarget: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null
)