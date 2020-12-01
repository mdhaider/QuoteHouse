package com.nehal.quotehouse.model

import com.google.gson.annotations.SerializedName

data class Mcq(
		@SerializedName("objectId")
		val objectId: String,
		@SerializedName("progLang")
		val progLang: String,
		@SerializedName("question")
		val question: String,
		@SerializedName("options")
		val options: List<String>,
		@SerializedName("createdAt")
		val createdAt: String,
		@SerializedName("updatedAt")
		val updatedAt: String,
		@SerializedName("answer")
		val answer: Int,
		@SerializedName("difficultyLevel")
		val difficultyLevel: Int
)