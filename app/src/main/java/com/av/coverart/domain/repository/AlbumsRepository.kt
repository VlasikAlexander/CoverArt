package com.av.coverart.domain.repository

import com.av.coverart.data.remote.dto.LFM

interface AlbumsRepository {

    suspend fun searchAlbums(album: String): LFM
}