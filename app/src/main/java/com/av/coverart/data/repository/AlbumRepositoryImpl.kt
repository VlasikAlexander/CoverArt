package com.av.coverart.data.repository

import com.av.coverart.data.remote.LastfmApi
import com.av.coverart.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: LastfmApi
) : AlbumsRepository {

    override suspend fun searchAlbums(album: String) = api.searchAlbums(album)

}