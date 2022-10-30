package com.antigua.mynoteroom.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antigua.mynoteroom.model.NoteEntity

@Composable
fun NoteListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    note: NoteEntity,
) {
}