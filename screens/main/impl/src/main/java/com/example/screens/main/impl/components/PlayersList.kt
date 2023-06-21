package com.example.screens.main.impl.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.screens.main.api.data.Player
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.screens.main.api.state.MainScreenState
import com.example.utils.PlayerInfoCard
import com.example.utils.R


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
internal fun PlayersList(
    modifier: Modifier = Modifier,
    lazyColumnState: LazyListState,
    players: List<Player>,
    placeHolderDrawableRes: Int,
    showDialog: Boolean,
) {
    LazyColumn(
        modifier = modifier,
        state = lazyColumnState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            horizontal = 12.dp, vertical = 18.dp
        )
    ) {
        items(
            players.size,
            key = { index -> players[index].id }
        ) { index ->
            PlayerCard(
                player = players[index],
                placeHolderDrawableRes = placeHolderDrawableRes,
                modifier = Modifier
                    .combinedClickable(
                        onClick = { Log.d("AlertDialog", "onClick") },
                        onLongClick = {  }
                    )
            )
            if (showDialog){
                OnLongClick(showDialog = showDialog, onDismiss = {})
            }
            if (index != players.lastIndex)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun OnLongClick(
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            content = {
                PlayerInfoCard(
                    nickname = "Durachyo",
                    lastOnline = "12 hours ago",
                    avatarImagePainter = painterResource(id = R.drawable.dota2_icon_placeholder),
                    onProfileLinkIsClicked = { Log.d("AlertDialog", "onProfileLinkIsClicked") },
                    onSteamProfileLinkIsClicked = {
                        Log.d(
                            "AlertDialog",
                            "onSteamProfileLinkIsClicked"
                        )
                    },
                )
            },
            onDismissRequest = {
                onDismiss()
                Log.d("AlertDialog", "onDismissRequestIsClicked")
            },
        )
    }
}

@ExperimentalFoundationApi
@Preview(showSystemUi = true)
@ExperimentalMaterial3Api
@Composable
fun PlayersListPreview(){
    MaterialTheme{
        PlayersList(lazyColumnState = rememberLazyListState(), players = listOf(
            Player(
                id = "1234",
                nickname = "Leha",
                avatar = null
            ),
        ),
            placeHolderDrawableRes = com.example.impl.R.drawable.dota2_logo_icon,
            showDialog = true
        )
    }
}