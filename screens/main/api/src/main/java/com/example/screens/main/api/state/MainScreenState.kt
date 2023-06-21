package com.example.screens.main.api.state

import com.example.screens.main.api.data.Player

sealed class MainScreenState {

    object EmptyList : MainScreenState()

    data class Initiated(
        val players: List<Player>,
    ) : MainScreenState()

    data class DialogLongClick(
        val players: List<Player>,
        val showDialog: Boolean = true,
    ) : MainScreenState()
}
