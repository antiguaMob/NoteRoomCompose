package com.antigua.mynoteroom.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.antigua.mynoteroom.data.repository.NoteRepository
import com.antigua.mynoteroom.model.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface HomeViewModelAbstract {
    val selectedNoteState: State<NoteEntity?>
    val noteListFlow: Flow<List<NoteEntity>>
    fun addNote(note : NoteEntity)
    fun updateNote(note : NoteEntity)
    fun deleteNote(note : NoteEntity)
    fun selectedNote(note: NoteEntity)
}

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val noteRepository: NoteRepository,
): ViewModel(), HomeViewModelAbstract{

    private  val ioScope = CoroutineScope(Dispatchers.IO)
    private val _selectedState: MutableState<NoteEntity?> = mutableStateOf(null)
    override val selectedNoteState: State<NoteEntity?>
        get() = _selectedState


    override val noteListFlow: Flow<List<NoteEntity>> = noteRepository.getAllFlow()


    override fun addNote(note: NoteEntity) {
        ioScope.launch {
            noteRepository.insert(note = note)
        }
    }

    override fun updateNote(note: NoteEntity) {
        ioScope.launch {
            noteRepository.update(note = note)
        }
    }
    override fun deleteNote(note: NoteEntity){
        ioScope.launch {
            noteRepository.delete(note = note)
        }
    }

    override fun selectedNote(note: NoteEntity) {
        _selectedState.value = note
    }
}