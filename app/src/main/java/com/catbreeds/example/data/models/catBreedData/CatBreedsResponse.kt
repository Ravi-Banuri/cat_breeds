package com.catbreeds.example.data.models.catBreedData

import com.catbreeds.example.data.models.catBreedData.CatBreed
import com.google.gson.annotations.SerializedName

data class CatBreedsResponse(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("data") val data: List<CatBreed>,
    @SerializedName("first_page_url") val firstPageUrl: String,
    val from: Int,
    @SerializedName("last_page_url") val lastPageUrl: String,
    val links: List<PageLink>,
    @SerializedName("next_page_url") val nextPageUrl: String?,
    val path: String,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("prev_page_url") val prevPageUrl: String?,
    val to: Int,
    val total: Int
)
