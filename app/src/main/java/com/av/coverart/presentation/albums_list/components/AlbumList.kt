package com.av.coverart.presentation.albums_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.av.coverart.presentation.albums_list.AlbumListViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(
    viewModel: AlbumListViewModel = hiltViewModel()
) {

    val searchState = remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        TextField(
            value = searchState.value,
            onValueChange = { searchState.value = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Black,
                focusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Black,
                disabledIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                disabledLabelColor = Color.Black,
                cursorColor = Color.White,
                errorLabelColor = Color.Black,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Color.Gray, shape = CircleShape),
        )
        Button(onClick =  { viewModel.getAlbums(searchState.value) }) {
            Text(text = "Search")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(16.dp)
        ) {
            items(viewModel.state.value.albums) {
                if (it.images[2].isNotEmpty()) {
                    GlideImage(
                        model = it.images[2],
                        contentDescription = null,
                        modifier = Modifier
                            .size(500.dp)

                            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                            .border(1.dp, Color.White, shape = MaterialTheme.shapes.medium)
                    )
                    Text(
                        text = it.name,
                        fontSize = 24.sp,
                        color = Color.White,
                        style = TextStyle(textAlign = TextAlign.Center),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                        )
                }
            }
        }
    }
}
