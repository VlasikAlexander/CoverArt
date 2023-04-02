package com.av.coverart.presentation.albums_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.av.coverart.domain.usecase.get_albums.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AlbumsListState())
    val state: State<AlbumsListState> = _state

    init {
        println("viewModelScope init")
        getAlbums("чайф")
    }

     fun getAlbums(album: String) {
        getAlbumsUseCase(album).onEach { result ->
            when (result) {
                is com.av.coverart.common.Resource.Loading -> {
                    _state.value = AlbumsListState(isLoading = true)
                }
                is com.av.coverart.common.Resource.Success -> {
                    println("result.data ${result.data}")
                    _state.value = AlbumsListState(albums = result.data ?: emptyList())
                }
                is com.av.coverart.common.Resource.Error -> {
                    _state.value = AlbumsListState(error = result.message ?: "An unexpected error occurred")
                }
            }

        }.launchIn(viewModelScope)
    }

}
