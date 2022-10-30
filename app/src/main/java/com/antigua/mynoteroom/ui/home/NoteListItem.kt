package com.antigua.mynoteroom.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.antigua.mynoteroom.model.NoteEntity

@Composable
fun NoteListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    note: NoteEntity,
) {
    Box(modifier = modifier
        .fillMaxWidth()
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
//**************************************************************************************************
        .height(54.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp, end = 16.dp),
            text = note.text,
            maxLines = 1,
        )
        Spacer(modifier = Modifier
            .height(0.7.dp)
            .fillMaxWidth()
            .background(color = Color.Gray.copy(alpha = 0.54f))
            .align(Alignment.BottomCenter))
    }
}