package com.codepath.bestsellerlistapp

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
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

//    @SerializedName("description")
//    var description: String? = null

}