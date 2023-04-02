package com.av.coverart.data.remote.dto

import com.av.coverart.domain.model.AlbumUi
import com.google.gson.annotations.SerializedName

data class LFM(
    val results: Results
)

data class Results(
    @SerializedName("opensearch")
    val openSearch: Opensearch,
    @SerializedName("albummatches")
    val albumMatches: AlbumMatches
)

data class Opensearch(
    val totalResults: String,
    val startIndex: String,
    val itemsPerPage: String
)

data class AlbumMatches(
    val album: List<Album>
)

data class Album(
    val name: String,
    val artist: String,
    val url: String,
    val image: List<Image>,
    val streamable: String,
    val mbid: String
)

data class Image(
    val size: String,
    @SerializedName("#text")
    val text: String
)

fun LFM.toAlbumUi(): List<AlbumUi> {
    println("results.albumMatches.album: ${results.albumMatches.album}")
    return results.albumMatches.album.map {
        AlbumUi(
            name = it.name,
            artist = it.artist,
            images = it.image.map { image -> image.text }
        )
    }
}
