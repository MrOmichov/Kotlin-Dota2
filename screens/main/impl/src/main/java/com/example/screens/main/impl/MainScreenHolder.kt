package com.example.screens.main.impl

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.screens.main.api.state.MainScreenState

@ExperimentalFoundationApi
@Composable
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
fun MainScreenHolder(
    viewModel: MainScreenViewModel
) {
    val screenState by viewModel.state.collectAsState()

    when (val state = screenState) {
        is MainScreenState.EmptyList ->
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                players = emptyList(),
                coroutineScope = rememberCoroutineScope(),
                scaffoldState = rememberScaffoldState(),
                lazyColumnState = rememberLazyListState(),
                textFieldValue = remember {
                    mutableStateOf(
                        TextFieldValue("")
                    )
                },
                showDialog = false,
            )

        is MainScreenState.Initiated ->
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                players = state.players,
                coroutineScope = rememberCoroutineScope(),
                scaffoldState = rememberScaffoldState(),
                lazyColumnState = rememberLazyListState(),
                textFieldValue = remember {
                    mutableStateOf(
                        TextFieldValue("")
                    )
                },
                showDialog = false,
            )

        is MainScreenState.DialogLongClick ->
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                players = state.players,
                coroutineScope = rememberCoroutineScope(),
                scaffoldState = rememberScaffoldState(),
                lazyColumnState = rememberLazyListState(),
                textFieldValue = remember {
                    mutableStateOf(
                        TextFieldValue("")
                    )
                },
                showDialog = state.showDialog
            )
    }

    LaunchedEffect(Unit) {
        viewModel.searchPlayers("")
    }
}