package com.antigua.mynoteroom.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.antigua.mynoteroom.R
import com.antigua.mynoteroom.model.NoteEntity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    note: NoteEntity,
    dismissState: DismissState,
) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                when(dismissState.targetValue){
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> Color.Red
                    DismissValue.DismissedToStart -> Color.Red
                }
            )
            val alignment = when(direction){
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val iconTint = when(dismissState.targetValue){
               DismissValue.Default -> Color.Red
                DismissValue.DismissedToStart -> Color.White
                DismissValue.DismissedToEnd -> Color.White

            }
            val iconScale by animateFloatAsState(
                if(dismissState.targetValue == DismissValue.Default) 0.8f else 1f
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = color)
                    .padding(horizontal = 16.dp),
                contentAlignment = alignment,
            ){
                Icon(
                    modifier = Modifier
                        .scale(iconScale),
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(
                        id = R.string.screen_home_swipe_delete_note),
                    tint = iconTint,
                )
            }
        }
    ){
        Box(modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onDelete()
                    }
                )
            }
            .clickable {
                onClick()
            }
            .height(54.dp)
        ) {
           Row (
               modifier = Modifier.align(Alignment.CenterStart)
                   ){
               Text(
                   modifier = Modifier
                       .padding(start = 16.dp, end = 16.dp)
                       .align(Alignment.CenterVertically)
                       .weight(1f),
                   text = note.text,
                   maxLines = 1,
               )
               Icon(
                   modifier =Modifier
                       .align(Alignment.CenterVertically)
                       .alpha(0.72f)
                       .padding(end = 16.dp),
                   imageVector = Icons.Rounded.EditNote,
                   contentDescription = "Edit Note",
               )
           }
            Spacer(
                modifier = Modifier
                    .height(0.7.dp)
                    .fillMaxWidth()
                    .background(color = Color.Gray.copy(alpha = 0.54f))
                    .align(Alignment.BottomCenter)
            )
        }
    }

}