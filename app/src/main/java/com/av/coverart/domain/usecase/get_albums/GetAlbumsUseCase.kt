package com.av.coverart.domain.usecase.get_albums

import com.av.coverart.common.Resource
import com.av.coverart.data.remote.dto.toAlbumUi
import com.av.coverart.domain.model.AlbumUi
import com.av.coverart.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {

     operator fun invoke(album: String): Flow<Resource<List<AlbumUi>>> = flow {
        emit(Resource.Loading())
        try {
            val albums = repository.searchAlbums(album).toAlbumUi()
            emit(Resource.Success(albums))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}