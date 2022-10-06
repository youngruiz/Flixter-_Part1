package com.codepath.bestsellerlistapp

import com.google.gson.annotations.SerializedName

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

@Keep
@Serializable
data class SearchTvResponse(
    @SerialName("response")
    val response: BaseResponse?
)

@Keep
@Serializable
data class BaseResponse(
    @SerialName("docs")
    val docs: List<BestSellerBook>?
)

@Keep
@Serializable
data class BestSellerBook2(
    @SerialName("original_title")
    val original_title: String?,
    @SerialName("overview")
    var overview: String?,
    @SerialName("multimedia")
    var multimedia: List<MultiMedia>
)
    : java.io.Serializable {
    val mediaImageUrl = "https://www.nytimes.com/${multimedia?.firstOrNull { it.url != null }?.url ?: ""}"
}

@Keep
@Serializable
data class OriginalTitle(
    @SerialName("original_title")
    val original_title: String?
):java.io.Serializable

@Keep
@Serializable
data class Overview(
    @SerialName("overview")
    val overview: String?
):java.io.Serializable

@Keep
@Serializable
data class MultiMedia(
    @SerialName("poster_url")
    val url: String?
):java.io.Serializable








class BestSellerBook {

    @JvmField
    @SerializedName("original_title")
    var original_title: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    //TODO bookImageUrl
    @SerializedName("poster_path")
    var poster_path: String? = null

    @SerializedName("description")
    var description: String? = null

}