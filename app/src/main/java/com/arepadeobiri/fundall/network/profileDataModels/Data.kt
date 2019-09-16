package com.arepadeobiri.fundall.network.profileDataModels


import com.google.gson.annotations.SerializedName


data class Data(

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("monthly_target")
	val monthlyTarget: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null
)