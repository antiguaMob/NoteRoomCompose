package com.antigua.mynoteroom.viewmodel

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
    val noteListFlow: Flow<List<NoteEntity>>
    fun addNote(note : NoteEntity)
    fun updateNote(note : NoteEntity)
    fun deleteNote(note : NoteEntity)
}

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val noteRepository: NoteRepository,
): ViewModel(), HomeViewModelAbstract{

    private  val ioScope = CoroutineScope(Dispatchers.IO)

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
}