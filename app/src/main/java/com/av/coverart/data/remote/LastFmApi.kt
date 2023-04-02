package com.av.coverart.data.remote

import com.av.coverart.common.Constants
import com.av.coverart.data.remote.dto.LFM
import retrofit2.http.GET
import retrofit2.http.Query

interface LastfmApi {

    @GET("?method=album.search")
    suspend fun searchAlbums(
        @Query("album") album: String,
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Query("format") format : String = "json"
    ) : LFM

}

