package com.antigua.mynoteroom.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antigua.mynoteroom.viewmodel.HomeViewModelAbstract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: HomeViewModelAbstract
) {
    Scaffold {
        val note = viewModel.selectedNoteState.value
        Text(
            modifier = Modifier.padding(it),
            text = note?.text ?: "empty"
        )
    }
}