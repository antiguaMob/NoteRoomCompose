@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("OPT_IN_NOT_ENABLED")

package com.antigua.mynoteroom.ui.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antigua.mynoteroom.HOME_BUTTON_NOTE_ADD
import com.antigua.mynoteroom.HOME_ITEM_NOTE
import com.antigua.mynoteroom.HOME_TITLE
import com.antigua.mynoteroom.R
import com.antigua.mynoteroom.model.NoteEntity
import com.antigua.mynoteroom.viewmodel.HomeViewModelAbstract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModelAbstract,
    onClickNote: (NoteEntity) -> Unit,
    onClickAddNote: () -> Unit,


){
    val noteListState = homeViewModel.noteListFlow.collectAsState(initial = listOf())
    val txtState = rememberSaveable{ mutableStateOf("") }
    val noteIdState :MutableState<Long?> = rememberSaveable{ mutableStateOf(null) }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(title = {
              Text(
                  modifier = Modifier.testTag(HOME_TITLE),
                  text = stringResource(id = R.string.app_name))
            },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp ),
                        imageVector = Icons.Rounded.Notes ,
                        contentDescription = null
                    )
                }
            ) 
        }
            ) { contentPadding ->  // New Compose fitch
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            items(
                items = noteListState.value,
                 key = { it.roomId ?: ""}
            ) { note ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it == DismissValue.DismissedToStart ||
                            it == DismissValue.DismissedToEnd){
                            // delete the item from database
                            homeViewModel.deleteNote(note)

                            return@rememberDismissState true
                        }
                        return@rememberDismissState false
                    }
                )
                NoteListItem(
                    modifier = Modifier
                        .testTag(HOME_ITEM_NOTE)
                        .animateItemPlacement(),
                    onClick = {
                        noteIdState.value = note.roomId
                        txtState.value = note.text
                        homeViewModel.selectedNote(note)
                        onClickNote(note)
                    },
                    onDelete = {
                        // delete the note on long click
                        homeViewModel.deleteNote(note)
                    },
                    note = note,
                    dismissState = dismissState
                )
            }
            item(key = "add_button") {
                Box(modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .testTag(HOME_BUTTON_NOTE_ADD)
                            .align(Alignment.Center),
                        onClick = {
                            homeViewModel.resetSelectedNote()
                            onClickAddNote()
                        }) {
                        Text(text = stringResource(R.string.screen_home_button_add_note))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen(){
    HomeScreen(
        homeViewModel = object : HomeViewModelAbstract {
            override val selectedNoteState: State<NoteEntity?>
                get() = mutableStateOf(null)
            override val noteListFlow: Flow<List<NoteEntity>>
                get() = flowOf(
                    listOf(
                        NoteEntity(text = "note 1"),
                        NoteEntity(text = "note 2"),
                        NoteEntity(text = "note 3"),
                        NoteEntity(text = "note 4"),
                        NoteEntity(text = "note 5"),
                    )
                )

            override fun addOrUpdateNote(note: NoteEntity){}
            override fun deleteNote(note: NoteEntity){}
            override fun selectedNote(note: NoteEntity) {}
            override fun resetSelectedNote() {}
        },
        onClickNote = {},
        onClickAddNote = {},
    )
}