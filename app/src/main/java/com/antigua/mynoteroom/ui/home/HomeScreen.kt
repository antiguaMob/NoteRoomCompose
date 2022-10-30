@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("OPT_IN_NOT_ENABLED")

package com.antigua.mynoteroom.ui.home



import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antigua.mynoteroom.R
import com.antigua.mynoteroom.model.NoteEntity
import com.antigua.mynoteroom.viewmodel.HomeViewModelAbstract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModelAbstract,
    onClickNote: (NoteEntity) -> Unit,
    onClickAddNote: () -> Unit,


){
    val noteListState = homeViewModel.noteListFlow.collectAsState(initial = listOf())
    val txtState = rememberSaveable{ mutableStateOf("") }
    val noteIdState :MutableState<Long?> = rememberSaveable{ mutableStateOf(null) }

    Scaffold  { contentPadding ->  // New Compose fitch
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            items(noteListState.value.size) { index ->
                val note = noteListState.value[index]

                Box(modifier = Modifier
                    .fillMaxWidth()
//**************************************************************************************************
//                    .combinedClickable(
//                        onClick = {
//                            noteIdState.value = note.roomId
//                            txtState.value = note.text
//                            popupState.value = PopupState.Edit
//                        },
//                        onLongClick = {
//                            // delete the note on long click
//                            homeViewModel.deleteNote(note)
//                        })
//**************************************************************************************************
//                    .clickable(
//                        onClick = {
//                            noteIdState.value = note.roomId
//                            txtState.value = note.text
//                            popupState.value = PopupState.Edit
//                        },
//                    )
//**************************************************************************************************
                    .pointerInput(Unit) {
                        detectTapGestures(
//                          onPress = { /* Called when the gesture starts */ },
//                          onDoubleTap = { /* Called on Double Tap */ },
//                          onLongPress = { /* Called on Long Press */ },
//                          onTap = { /* Called on Tap */ }

                            onTap = {
                                noteIdState.value = note.roomId
                                txtState.value = note.text
                                //popupState.value = PopupState.Edit
                                homeViewModel.selectedNote(note)
                                onClickNote(note)
                            },
                            onLongPress = {
                                // delete the note on long click
                                homeViewModel.deleteNote(note)
                            }
                        )
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
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier
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
                get() = flowOf(listOf(
                    NoteEntity(text = "note 1"),
                    NoteEntity(text = "note 2"),
                    NoteEntity(text = "note 3"),
                    NoteEntity(text = "note 4"),
                    NoteEntity(text = "note 5"),
                ))

            override fun addOrUpdateNote(note: NoteEntity){}
            override fun deleteNote(note: NoteEntity){}
            override fun selectedNote(note: NoteEntity) {}

            override fun resetSelectedNote() {}
        },
        onClickNote = {},
        onClickAddNote = {},
    )
}