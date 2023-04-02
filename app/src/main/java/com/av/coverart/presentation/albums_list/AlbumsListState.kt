package com.av.coverart.presentation.albums_list

import com.av.coverart.domain.model.AlbumUi

data class AlbumsListState(
    val isLoading: Boolean = false,
    val albums: List<AlbumUi> = emptyList(),
    val error: String = ""
)
